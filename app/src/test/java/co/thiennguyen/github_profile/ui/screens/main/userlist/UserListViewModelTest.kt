package co.thiennguyen.github_profile.ui.screens.main.userlist

import co.thiennguyen.github_profile.test.CoroutineTestRule
import co.thiennguyen.github_profile.util.DispatchersProvider
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule

@ExperimentalCoroutinesApi
class UserListViewModelTest {

    @get:Rule
    val coroutinesRule = CoroutineTestRule()

//    private val mockUseCase: UseCase = mockk()

    private lateinit var viewModel: UserListViewModel

    @Before
    fun setUp() {
//        every { mockUseCase() } returns flowOf(MockUtil.models)

        initViewModel()
    }

//    @Test
//    fun `When loading models successfully, it shows the model list`() = runTest {
//        viewModel.users.test {
//            expectMostRecentItem() shouldBe MockUtil.models.map { it.toUiModel() }
//        }
//    }
//
//    @Test
//    fun `When loading models failed, it shows the corresponding error`() = runTest {
//        val error = Exception()
//        every { mockUseCase() } returns flow { throw error }
//        initViewModel(dispatchers = CoroutineTestRule(StandardTestDispatcher()).testDispatcherProvider)
//
//        viewModel.error.test {
//            advanceUntilIdle()
//
//            expectMostRecentItem() shouldBe error
//        }
//    }
//
//    @Test
//    fun `When loading models, it shows and hides loading correctly`() = runTest {
//        initViewModel(dispatchers = CoroutineTestRule(StandardTestDispatcher()).testDispatcherProvider)
//
//        viewModel.isLoading.test {
//            awaitItem() shouldBe false
//            awaitItem() shouldBe true
//            awaitItem() shouldBe false
//        }
//    }

    private fun initViewModel(dispatchers: DispatchersProvider = coroutinesRule.testDispatcherProvider) {
        viewModel = UserListViewModel(
            dispatchers,
        )
    }
}
