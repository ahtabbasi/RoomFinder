package com.ahtabbasi.roomlist.presentation.view

import app.cash.turbine.test
import com.ahtabbasi.roomlist.data.remote.api.ApiService
import com.ahtabbasi.roomlist.testutils.FakeData
import com.ahtabbasi.roomlist.testutils.MainCoroutineRule
import com.google.common.truth.Truth.assertThat
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
internal class RoomListViewModelTest {

    @Rule
    @JvmField
    val mainCoroutineRule = MainCoroutineRule()

    @RelaxedMockK
    private lateinit var apiService: ApiService

    private lateinit var injector: TestDependencyInjector

    private lateinit var sut: RoomListViewModel

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        injector = TestDependencyInjector(apiService).initialize()
    }

    @Test
    fun `given the api is working fine, when ViewModel initialized, then view state and loading should be updated properly`() =
        runTest {

            coEvery { apiService.getAllRooms() } returns FakeData.roomsDTO
            // initializing sut here because we want to mock the behavior of the init method in the sut
            sut = RoomListViewModel(injector.getAllRoomsUseCase, injector.setBookedRoomUseCase)

            launch {
                sut.viewState.test {
                    val state = awaitItem()
                    assertThat(state).isInstanceOf(RoomListViewState.ShowRooms::class.java)
                    assertThat((state as RoomListViewState.ShowRooms).rooms).isEqualTo(FakeData.rooms)
                    cancelAndConsumeRemainingEvents()
                }
                sut.loading.test {
                    assertThat(awaitItem()).isFalse()
                    cancelAndConsumeRemainingEvents()
                }
            }.join()
        }
}