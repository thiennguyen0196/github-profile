package co.thiennguyen.github_profile.ui.screens.userdetail

import app.cash.turbine.test
import co.thiennguyen.github_profile.domain.usecases.GetUserDetailUseCase
import co.thiennguyen.github_profile.test.CoroutineTestRule
import co.thiennguyen.github_profile.test.MockUtil
import co.thiennguyen.github_profile.ui.models.toUiModel
import co.thiennguyen.github_profile.util.DispatchersProvider
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class UserDetailViewModelTest {

    @get:Rule
    val coroutinesRule = CoroutineTestRule()

    private val mockUseCase: GetUserDetailUseCase = mockk()

    private lateinit var viewModel: UserDetailViewModel

    @Before
    fun setUp() {
        every { mockUseCase(any()) } returns flowOf(MockUtil.users.first())

        initViewModel()
    }

    @Test
    fun `When loading user successfully, it shows the user detail`() = runTest {
        viewModel.getUserDetail("userName")
        viewModel.user.test {
            expectMostRecentItem() shouldBe MockUtil.users.first().toUiModel()
        }
    }

    @Test
    fun `When loading user failed, it shows the corresponding error`() = runTest {
        val error = Exception()
        every { mockUseCase(any()) } returns flow { throw error }
        initViewModel(dispatchers = CoroutineTestRule(StandardTestDispatcher()).testDispatcherProvider)

        viewModel.getUserDetail("userName")
        viewModel.error.test {
            advanceUntilIdle()

            expectMostRecentItem() shouldBe error
        }
    }

    @Test
    fun `When loading user, it shows and hides loading correctly`() = runTest {
        initViewModel(dispatchers = CoroutineTestRule(StandardTestDispatcher()).testDispatcherProvider)

        viewModel.getUserDetail("userName")
        viewModel.isLoading.test {
            awaitItem() shouldBe false
            awaitItem() shouldBe true
            awaitItem() shouldBe false
        }
    }

    private fun initViewModel(dispatchers: DispatchersProvider = coroutinesRule.testDispatcherProvider) {
        viewModel = UserDetailViewModel(
            dispatchers,
            mockUseCase,
        )
    }
}
