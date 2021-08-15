package br.com.derlandybelchior.goodplaces.domain

import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import java.util.*

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class FetchLocationUseCaseTest {

    private val testDispatcher = TestCoroutineDispatcher()

    @MockK
    private lateinit var locationRepository: LocationRepository
    private lateinit var fetchLocationUseCase: FetchLocationUseCase

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        Dispatchers.setMain(testDispatcher)
        fetchLocationUseCase = FetchLocationUseCase(locationRepository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun fetchAll_whenSuccess_shouldReturnListOfLocation() = runBlocking {
        coEvery { locationRepository.fetchAll() } returns locationList

        val result = fetchLocationUseCase.fetchAllLocations()

        assertTrue(result is Resource.Success)
        assertEquals((result as Resource.Success).data, locationList)
    }

    @Test
    fun fetch_whenSuccess_shouldReturnALocationDetail() = runBlocking {
        coEvery { locationRepository.fetch(1) } returns locationDetail

        val result = fetchLocationUseCase.fetch(1)

        assertEquals(locationDetail, (result as Resource.Success).data)
    }

    @Test
    fun fetch_whenError_shouldReturnAResourceError() = runBlocking {
        coEvery { locationRepository.fetch(1) } throws Throwable()

        val result = fetchLocationUseCase.fetch(1)

        assertTrue(result is Resource.Error)
    }

    companion object {
        private val locationList by lazy {
            listOf(
                Location(
                    id = 1,
                    name = "Café Escritório",
                    review = 3.8,
                    type = "Coworking"
                ),
                Location(
                    id = 2,
                    name = "Hangar",
                    review = 3.8,
                    type = "Restaurante"
                ),
                Location(
                    id = 3,
                    name = "Padaria Pelicano",
                    review = 4.1,
                    type = "Padaria"
                )
            )
        }

        private val locationDetail by lazy {
            LocationDetail(
                id = 2,
                name = "Hangar",
                review = 3.8,
                type = "Restaurante",
                about = "",
                address = "",
                phone = "",
                schedule = listOf(
                    Schedule(
                        open = "7h",
                        close = "13h",
                        dayOfWeek = Calendar.MONDAY
                    )
                )
            )
        }
    }
}