package com.ahtabbasi.roomlist.data.repositories

import com.ahtabbasi.domain.models.Room
import com.ahtabbasi.roomlist.data.local.datasources.RoomLocalDataSource
import com.ahtabbasi.roomlist.data.remote.datasources.RoomRemoteDataSource
import com.ahtabbasi.roomlist.domain.models.GetAllRoomsResult
import com.ahtabbasi.roomlist.domain.repositories.RoomRepository
import com.ahtabbasi.roomlist.testutils.FakeData
import com.ahtabbasi.utils.network.NetworkError
import com.ahtabbasi.utils.network.NetworkResult
import com.google.common.truth.Truth
import io.mockk.*
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class RoomRepositoryImplTest {

    @RelaxedMockK
    private lateinit var localDataSource: RoomLocalDataSource

    @RelaxedMockK
    private lateinit var remoteDataSource: RoomRemoteDataSource

    private lateinit var sut: RoomRepository

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        sut = RoomRepositoryImpl(localDataSource, remoteDataSource)
    }

    @Test
    fun `given cache has data, getAllRooms should return cache data`() = runTest {

        val roomsFromCache = mockk<List<Room>>()
        coEvery { localDataSource.getAllRooms() } returns roomsFromCache

        val output = sut.getAllRooms()

        Truth.assertThat(output).isInstanceOf(GetAllRoomsResult.Success::class.java)
        Truth.assertThat((output as GetAllRoomsResult.Success).rooms).isEqualTo(
            roomsFromCache
        )
    }

    @Test
    fun `given cache has no data and api returns valid data, getAllRooms should return api data and update cache`() =
        runTest {

            coEvery { localDataSource.getAllRooms() } returns null
            coEvery { remoteDataSource.getAllRooms() } returns NetworkResult.Success(FakeData.roomsDTO)

            val output = sut.getAllRooms()

            Truth.assertThat(output).isInstanceOf(GetAllRoomsResult.Success::class.java)
            Truth.assertThat((output as GetAllRoomsResult.Success).rooms).isEqualTo(FakeData.rooms)
            coVerify { localDataSource.saveRooms(FakeData.rooms) }
        }

    @Test
    fun `given cache has no data and api returns error, getAllRooms should return the correct error`() =
        runTest {

            val mockError = mockk<NetworkError>()
            coEvery { localDataSource.getAllRooms() } returns null
            coEvery { remoteDataSource.getAllRooms() } returns NetworkResult.Failure(mockError)

            val output = sut.getAllRooms()

            Truth.assertThat(output).isInstanceOf(GetAllRoomsResult.Error::class.java)
            Truth.assertThat((output as GetAllRoomsResult.Error).error).isEqualTo(mockError)
        }

    @Test
    fun `bookRoom must update cache data`() = runTest {

        val mockRoom = mockk<Room>()
        sut.bookRoom(mockRoom)

        coVerify { localDataSource.bookRoom(mockRoom) }
    }
}