package com.bmtriet.simpleweather.presentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bmtriet.simpleweather.di.IoDispatcher
import com.bmtriet.simpleweather.domain.usecase.CityForecastParam
import com.bmtriet.simpleweather.domain.usecase.GetDailyForecastUseCase
import com.bmtriet.simpleweather.presentation.model.DailyForestUiModel
import com.bmtriet.simpleweather.presentation.model.DailyForestUiModelMapper
import com.bmtriet.simpleweather.utils.DataResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class ForecastViewModel @Inject constructor(
    private val dailyForecastsUseCase: GetDailyForecastUseCase,
    @IoDispatcher private val defaultDispatcher: CoroutineDispatcher,
    private val uiModelMapper: DailyForestUiModelMapper,
) : ViewModel() {

    private val _dailyForecast = MutableLiveData<List<DailyForestUiModel>?>(emptyList())
    val dailyForecastLiveData: LiveData<List<DailyForestUiModel>?> = _dailyForecast

    private val _loadError = MutableLiveData<DataResult.Error>()
    val loadErrorLiveData: LiveData<DataResult.Error> = _loadError

    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean> = _isLoading

    fun getNextDaysForecasts(cityName: String, forceReload: Boolean) {
        _isLoading.postValue(true)
        viewModelScope.launch(defaultDispatcher) {
            dailyForecastsUseCase.invoke(
                CityForecastParam(cityName, forceReload),
            ).collectLatest { result ->
                Timber.d("collectLatest: $result")
                when (result) {
                    is DataResult.Success -> {
                        val uiModels = result.data!!.dailyForecasts.map {
                            uiModelMapper.toUiModel(it)
                        }
                        _dailyForecast.postValue(uiModels)
                    }
                    is DataResult.Error -> {
                        _loadError.postValue(result)
                    }
                }
                _isLoading.postValue(false)
            }
        }
    }
}
