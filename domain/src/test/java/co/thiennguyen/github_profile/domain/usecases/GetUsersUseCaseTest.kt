package co.thiennguyen.github_profile.domain.usecases

import co.thiennguyen.github_profile.domain.repositories.UserRepository
import co.thiennguyen.github_profile.domain.test.MockUtil
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class GetUsersUseCaseTest {

    private lateinit var mockRepository: UserRepository
    private lateinit var useCase: GetUsersUseCase

    @Before
    fun setUp() {
        mockRepository = mockk()
        useCase = GetUsersUseCase(mockRepository)
    }

    @Test
    fun `When calling get users use case successful, it returns success`() = runTest {
        val expected = MockUtil.users
        every { mockRepository.getUsers(any(), any()) } returns flowOf(expected)

        useCase(GetUsersUseCase.Input(0, 20)).collect {
            it shouldBe expected
        }
    }

    @Test
    fun `When calling get users use case failed, it returns error`() = runTest {
        val expected = Exception()
        every { mockRepository.getUsers(any(), any()) } returns flow { throw expected }

        useCase(GetUsersUseCase.Input(0, 20)).catch {
            it shouldBe expected
        }.collect()
    }
}
