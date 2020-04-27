import com.javiersc.resources.resource.Resource
import utils.DataAndCounters
import utils.folding
import utils.ifFolding
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertTrue

@Suppress("MagicNumber")
internal class SuccessTest {

    private val dataAndCounters = DataAndCounters()

    @BeforeTest
    fun `clear all data and counters`() = dataAndCounters.clearAll()

    @Test
    fun `check callbacks when Resource is Success and has data`() {
        var resource: Resource<String, String> = Resource.Success(DataAndCounters.SUCCESS_DATA)

        assertTrue { resource is Resource.Success }
        assertTrue { resource.isSuccess }

        folding(resource, dataAndCounters)

        with(dataAndCounters) {
            assertTrue { loadingCount == 0 }
            assertTrue { noLoadingCount == 1 }
            assertTrue { successData == DataAndCounters.SUCCESS_DATA }
            assertTrue { successCount == 1 }
            assertTrue { successEmptyCount == 0 }
            assertTrue { noSuccessCount == 0 }
            assertTrue { errorData == DataAndCounters.NO_ERROR_DATA }
            assertTrue { errorCount == 0 }
            assertTrue { errorEmptyCount == 0 }
            assertTrue { noErrorCount == 1 }
            assertTrue { cacheData == DataAndCounters.NO_CACHE_DATA }
            assertTrue { cacheCount == 0 }
            assertTrue { cacheEmptyCount == 0 }
            assertTrue { noCacheCount == 1 }
        }

        resource = Resource.Success(DataAndCounters.SUCCESS_DATA_2)

        folding(resource, dataAndCounters)
        ifFolding(resource, dataAndCounters)

        with(dataAndCounters) {
            assertTrue { loadingCount == 0 }
            assertTrue { noLoadingCount == 3 }
            assertTrue { successData == DataAndCounters.SUCCESS_DATA_2 }
            assertTrue { successCount == 3 }
            assertTrue { successEmptyCount == 0 }
            assertTrue { noSuccessCount == 0 }
            assertTrue { errorData == DataAndCounters.NO_ERROR_DATA }
            assertTrue { errorCount == 0 }
            assertTrue { errorEmptyCount == 0 }
            assertTrue { noErrorCount == 3 }
            assertTrue { cacheData == DataAndCounters.NO_CACHE_DATA }
            assertTrue { cacheCount == 0 }
            assertTrue { cacheEmptyCount == 0 }
            assertTrue { noCacheCount == 3 }
        }
    }

    @Test
    fun `check callbacks when Resource is Success and does not have data`() {
        var resource: Resource<String, String> = Resource.Success(null)

        assertTrue { resource is Resource.Success<String> }
        assertTrue { resource.isSuccess }

        folding(resource, dataAndCounters)

        with(dataAndCounters) {
            assertTrue { loadingCount == 0 }
            assertTrue { noLoadingCount == 1 }
            assertTrue { successData == DataAndCounters.NO_SUCCESS_DATA }
            assertTrue { successCount == 0 }
            assertTrue { successEmptyCount == 1 }
            assertTrue { noSuccessCount == 0 }
            assertTrue { errorData == DataAndCounters.NO_ERROR_DATA }
            assertTrue { errorCount == 0 }
            assertTrue { errorEmptyCount == 0 }
            assertTrue { noErrorCount == 1 }
            assertTrue { cacheData == DataAndCounters.NO_CACHE_DATA }
            assertTrue { cacheCount == 0 }
            assertTrue { cacheEmptyCount == 0 }
            assertTrue { noCacheCount == 1 }
        }

        resource = Resource.Success(null)

        ifFolding(resource, dataAndCounters)
        folding(resource, dataAndCounters)

        with(dataAndCounters) {
            assertTrue { loadingCount == 0 }
            assertTrue { noLoadingCount == 3 }
            assertTrue { successData == DataAndCounters.NO_SUCCESS_DATA }
            assertTrue { successCount == 0 }
            assertTrue { successEmptyCount == 3 }
            assertTrue { noSuccessCount == 0 }
            assertTrue { errorData == DataAndCounters.NO_ERROR_DATA }
            assertTrue { errorCount == 0 }
            assertTrue { errorEmptyCount == 0 }
            assertTrue { noErrorCount == 3 }
            assertTrue { cacheData == DataAndCounters.NO_CACHE_DATA }
            assertTrue { cacheCount == 0 }
            assertTrue { cacheEmptyCount == 0 }
            assertTrue { noCacheCount == 3 }
        }
    }
}