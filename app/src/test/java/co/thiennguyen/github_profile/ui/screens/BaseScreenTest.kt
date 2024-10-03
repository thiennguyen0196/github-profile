package co.thiennguyen.github_profile.ui.screens

import co.thiennguyen.github_profile.test.CoroutineTestRule
import kotlinx.coroutines.test.StandardTestDispatcher

abstract class BaseScreenTest {

    protected val coroutinesRule = CoroutineTestRule()

    protected fun setStandardTestDispatcher() {
        coroutinesRule.testDispatcher = StandardTestDispatcher()
    }

    protected fun advanceUntilIdle() {
        coroutinesRule.testDispatcher.scheduler.advanceUntilIdle()
    }
}
