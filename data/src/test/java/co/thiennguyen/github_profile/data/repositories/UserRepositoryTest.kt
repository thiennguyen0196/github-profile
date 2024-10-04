package co.thiennguyen.github_profile.data.repositories

import co.thiennguyen.github_profile.data.local.dao.UserDao
import co.thiennguyen.github_profile.data.local.entity.toModels
import co.thiennguyen.github_profile.data.remote.models.responses.toModels
import co.thiennguyen.github_profile.data.remote.services.ApiService
import co.thiennguyen.github_profile.data.test.MockUtil
import co.thiennguyen.github_profile.domain.repositories.UserRepository
import io.kotest.matchers.shouldBe
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.just
import io.mockk.mockk
import io.mockk.runs
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class UserRepositoryTest {

    private lateinit var mockService: ApiService
    private lateinit var mockDao: UserDao
    private lateinit var repository: UserRepository

    @Before
    fun setUp() {
        mockService = mockk()
        mockDao = mockk()
        repository = UserRepositoryImpl(mockService, mockDao)
    }

    @Test
    fun `When calling getUsers successful with first page and empty DAO, it returns success`() =
        runTest {
            val expected = MockUtil.responses
            coEvery { mockService.getUsers(any(), any()) } returns expected
            coEvery { mockDao.getUsers() } returns emptyList()
            coEvery { mockDao.insertUsers(any()) } just runs

            repository.getUsers(0, 20).collect {
                it shouldBe expected.toModels()
            }
            coVerify(exactly = 1) { mockDao.insertUsers(any()) }
        }

    @Test
    fun `When calling getUsers successful with first page and not empty DAO, it returns success`() =
        runTest {
            val expected = MockUtil.entities
            coEvery { mockDao.getUsers() } returns expected

            repository.getUsers(0, 20).collect {
                it shouldBe expected.toModels()
            }
            coVerify(exactly = 0) { mockService.getUsers(any(), any()) }
            coVerify(exactly = 0) { mockDao.insertUsers(any()) }
        }

    @Test
    fun `When calling getUsers successful with not first page, it returns success`() =
        runTest {
            val expected = MockUtil.responses
            coEvery { mockService.getUsers(any(), any()) } returns expected

            repository.getUsers(2, 20).collect {
                it shouldBe expected.toModels()
            }
            coVerify(exactly = 0) { mockDao.getUsers() }
            coVerify(exactly = 0) { mockDao.insertUsers(any()) }
        }

    @Test
    fun `When calling getUsers failed, it returns error`() = runTest {
        val expected = Throwable()
        coEvery { mockService.getUsers(any(), any()) } throws expected

        repository.getUsers(2, 20).catch {
            it shouldBe expected
        }.collect()
    }
}
