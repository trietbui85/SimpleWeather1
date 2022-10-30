package com.bmtriet.simpleweather.domain.usecase

import app.cash.turbine.test
import com.bmtriet.simpleweather.domain.repository.ForecastRepository
import com.bmtriet.simpleweather.utils.DataResult
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class GetDailyForecastUseCaseTest {

    private val dispatcher = UnconfinedTestDispatcher()
    private val repository: ForecastRepository = TestForecastRepository()
    private val useCase = GetDailyForecastUseCase(repository, dispatcher)

    @Test
    fun testExecute() = runTest {
        val param = CityForecastParam(cityName = "city", forceReload = true)

        useCase.invoke(param).test {
            assertEquals(
                awaitItem(),
                DataResult.Success(TestForecastRepository.cityForecastModel),
            )
            awaitComplete()
        }
    }
}
