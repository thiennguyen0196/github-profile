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
class GetUserDetailUseCaseTest {

    private lateinit var mockRepository: UserRepository
    private lateinit var useCase: GetUserDetailUseCase

    @Before
    fun setUp() {
        mockRepository = mockk()
        useCase = GetUserDetailUseCase(mockRepository)
    }

    @Test
    fun `When calling get user detail use case successful, it returns success`() = runTest {
        val expected = MockUtil.users.first()
        every { mockRepository.getUserDetail(any()) } returns flowOf(expected)

        useCase("name").collect {
            it shouldBe expected
        }
    }

    @Test
    fun `When calling get user detail use case failed, it returns error`() = runTest {
        val expected = Exception()
        every { mockRepository.getUserDetail(any()) } returns flow { throw expected }

        useCase("name").catch {
            it shouldBe expected
        }.collect()
    }
}
