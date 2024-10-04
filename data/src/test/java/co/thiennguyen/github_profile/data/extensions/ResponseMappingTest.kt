package co.thiennguyen.github_profile.data.extensions

import co.thiennguyen.github_profile.data.remote.models.responses.toModel
import co.thiennguyen.github_profile.data.test.MockUtil
import co.thiennguyen.github_profile.domain.exceptions.ApiException
import co.thiennguyen.github_profile.domain.exceptions.NoConnectivityException
import co.thiennguyen.github_profile.domain.models.User
import io.kotest.matchers.shouldBe
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.test.runTest
import org.junit.Test
import java.io.IOException
import java.io.InterruptedIOException
import java.net.UnknownHostException

@ExperimentalCoroutinesApi
class ResponseMappingTest {

    @Test
    fun `When mapping API request flow failed with UnknownHostException, it returns mapped NoConnectivityException error`() =
        runTest {
            flowTransform<User> {
                throw UnknownHostException()
            }.catch {
                it shouldBe NoConnectivityException
            }.collect()
        }

    @Test
    fun `When mapping API request flow failed with InterruptedIOException, it returns mapped NoConnectivityException error`() =
        runTest {
            flowTransform<User> {
                throw InterruptedIOException()
            }.catch {
                it shouldBe NoConnectivityException
            }.collect()
        }

    @Test
    fun `When mapping API request flow failed with HttpException, it returns mapped ApiException error`() =
        runTest {
            val httpException = MockUtil.mockHttpException
            flowTransform<User> {
                throw httpException
            }.catch {
                it shouldBe ApiException(
                    MockUtil.errorResponse.toModel(),
                    httpException.code(),
                    httpException.message()
                )
            }.collect()
        }

    @Test
    fun `When mapping API request flow failed with unhandled exceptions, it should throw that error`() =
        runTest {
            val exception = IOException("Canceled")
            flowTransform<User> {
                throw exception
            }.catch {
                it shouldBe exception
            }.collect()
        }
}
