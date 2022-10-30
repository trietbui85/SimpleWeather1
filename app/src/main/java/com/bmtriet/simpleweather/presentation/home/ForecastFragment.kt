package com.bmtriet.simpleweather.presentation.home

import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.bmtriet.simpleweather.R
import com.bmtriet.simpleweather.databinding.FragmentHomeBinding
import com.bmtriet.simpleweather.presentation.adapter.DailyForecastAdapter
import com.bmtriet.simpleweather.utils.beInvisible
import com.bmtriet.simpleweather.utils.beVisible
import com.bmtriet.simpleweather.utils.beVisibleOtherwiseInvisible
import com.bmtriet.simpleweather.utils.hideKeyBoard
import com.bmtriet.simpleweather.utils.isNotNullAndNotEmpty
import com.bmtriet.simpleweather.utils.setOnSafeClick
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

/**
 * This screen, used to show current weather and forecast for next days and nearby places
 */
@AndroidEntryPoint
class ForecastFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<ForecastViewModel>()

    private val forecastAdapter: DailyForecastAdapter by lazy {
        DailyForecastAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()

        registerUiAction()

        observeDataChanged()
    }

    private fun initView() {
        binding.rvDailyForecast.run {
            adapter = forecastAdapter
            val divider = DividerItemDecoration(
                context,
                (layoutManager as LinearLayoutManager).orientation,
            )
            divider.setDrawable(
                AppCompatResources.getDrawable(
                    context,
                    R.drawable.horizontal_divider,
                )!!,
            )
            addItemDecoration(divider)
            setHasFixedSize(true)
        }
    }

    private fun registerUiAction() {
        binding.btnClear.setOnSafeClick {
            binding.edtCity.setText("")
        }

        binding.btnGetWeather.setOnSafeClick {
            performSearch()
        }

        binding.btnGetWeather.setOnLongClickListener {
            performSearch(forceReload = true)
            true
        }

        binding.edtCity.setOnKeyListener { _, keyCode, event ->
            if ((keyCode == KeyEvent.KEYCODE_ENTER || keyCode == KeyEvent.KEYCODE_SEARCH) &&
                event?.action == KeyEvent.ACTION_UP
            ) {
                performSearch()
                return@setOnKeyListener true
            }
            false
        }

        binding.edtCity.doOnTextChanged { text, _, _, _ ->
            binding.btnClear.beVisibleOtherwiseInvisible { text.isNotNullAndNotEmpty() }
        }
    }

    private fun performSearch(forceReload: Boolean = false) {
        val keyword = binding.edtCity.text.toString().trim()
        Timber.d("performSearch keyword=$keyword, forceReload=$forceReload")
        if (keyword.length < MINIMUM_SEARCH_KEYWORD_LENGTH) {
            val msg = getString(R.string.invalid_city_name, MINIMUM_SEARCH_KEYWORD_LENGTH)
            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
            return
        }
        activity.hideKeyBoard()
        viewModel.getNextDaysForecasts(cityName = keyword, forceReload)
    }

    private fun observeDataChanged() {
        viewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            Timber.d("isLoadingLiveData: $isLoading")
            binding.progressBar.beVisibleOtherwiseInvisible { isLoading }
        }

        viewModel.dailyForecastLiveData.observe(viewLifecycleOwner) { items ->
            Timber.d("dailyForecastLiveData: ${items?.size} days forecast")
            forecastAdapter.submitList(items.orEmpty())
            binding.errorView.beInvisible { true }
            binding.emptyView.beVisibleOtherwiseInvisible { items == null }
        }

        viewModel.loadErrorLiveData.observe(viewLifecycleOwner) { error ->
            Timber.d("loadErrorLiveData: $error")
            binding.errorView.let {
                it.beVisible { true }
                it.text = error.message
            }
            forecastAdapter.submitList(listOf())
        }
    }

    companion object {
        private const val MINIMUM_SEARCH_KEYWORD_LENGTH = 3
    }
}
