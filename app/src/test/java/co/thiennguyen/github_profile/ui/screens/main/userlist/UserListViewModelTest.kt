package co.thiennguyen.github_profile.ui.screens.main.userlist

import app.cash.turbine.test
import co.thiennguyen.github_profile.domain.usecases.GetUsersUseCase
import co.thiennguyen.github_profile.test.CoroutineTestRule
import co.thiennguyen.github_profile.test.MockUtil
import co.thiennguyen.github_profile.ui.models.toUiModel
import co.thiennguyen.github_profile.ui.screens.userlist.UserListViewModel
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
class UserListViewModelTest {

    @get:Rule
    val coroutinesRule = CoroutineTestRule()

    private val mockUseCase: GetUsersUseCase = mockk()

    private lateinit var viewModel: UserListViewModel

    @Before
    fun setUp() {
        every { mockUseCase(any()) } returns flowOf(MockUtil.users)

        initViewModel()
    }

    @Test
    fun `When loading users successfully, it shows the user list`() = runTest {
        viewModel.users.test {
            expectMostRecentItem() shouldBe MockUtil.users.map { it.toUiModel() }
        }
    }

    @Test
    fun `When loading models failed, it shows the corresponding error`() = runTest {
        val error = Exception()
        every { mockUseCase(any()) } returns flow { throw error }
        initViewModel(dispatchers = CoroutineTestRule(StandardTestDispatcher()).testDispatcherProvider)

        viewModel.error.test {
            advanceUntilIdle()

            expectMostRecentItem() shouldBe error
        }
    }

    @Test
    fun `When loading models, it shows and hides loading correctly`() = runTest {
        initViewModel(dispatchers = CoroutineTestRule(StandardTestDispatcher()).testDispatcherProvider)

        viewModel.isLoading.test {
            awaitItem() shouldBe false
            awaitItem() shouldBe true
            awaitItem() shouldBe false
        }
    }

    private fun initViewModel(dispatchers: DispatchersProvider = coroutinesRule.testDispatcherProvider) {
        viewModel = UserListViewModel(
            dispatchers,
            mockUseCase,
        )
    }
}
