package com.bmtriet.simpleweather.presentation.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.bmtriet.simpleweather.data.network.error.ErrorType
import com.bmtriet.simpleweather.domain.model.CityForecastModel
import com.bmtriet.simpleweather.domain.model.DailyForecastModel
import com.bmtriet.simpleweather.domain.usecase.GetDailyForecastUseCase
import com.bmtriet.simpleweather.presentation.model.DailyForestUiModel
import com.bmtriet.simpleweather.presentation.model.DailyForestUiModelMapper
import com.bmtriet.simpleweather.testutils.getOrAwaitValue
import com.bmtriet.simpleweather.utils.DataResult
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ForecastViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val dispatcher = UnconfinedTestDispatcher()
    private val useCase: GetDailyForecastUseCase = mockk()
    private val uiModelMapper: DailyForestUiModelMapper = mockk {
        every { toUiModel(dailyForecastModel) } returns dailyForestUiModel
    }

    private val viewModel = ForecastViewModel(useCase, dispatcher, uiModelMapper)

    @Before
    fun setUp() {
        Dispatchers.setMain(dispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun testGetNextDaysForecasts_Success() = runTest {
        coEvery {
            useCase.invoke(any())
        } returns flowOf(DataResult.Success(cityForecastModel))

        viewModel.getNextDaysForecasts("saigon", true)

        assertEquals(
            viewModel.dailyForecastLiveData.getOrAwaitValue(),
            listOf(dailyForestUiModel),
        )
        assertEquals(
            viewModel.isLoading.getOrAwaitValue(),
            false,
        )
    }

    @Test
    fun testGetNextDaysForecasts_Error() = runTest {
        coEvery {
            useCase.invoke(any())
        } returns flowOf(DataResult.Error(errorType))

        viewModel.getNextDaysForecasts("saigon", true)

        assertEquals(
            viewModel.loadErrorLiveData.getOrAwaitValue(),
            DataResult.Error(errorType),
        )
        assertEquals(
            viewModel.isLoading.getOrAwaitValue(),
            false,
        )
    }

    companion object {
        private val errorType = ErrorType.INVALID_CITY_ID
        private val dailyForecastModel = DailyForecastModel()
        private val cityForecastModel = CityForecastModel(
            dailyForecasts = listOf(dailyForecastModel),
        )
        private val dailyForestUiModel: DailyForestUiModel = mockk()
    }
}
