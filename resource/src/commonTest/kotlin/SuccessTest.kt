import com.javiersc.resources.resource.Resource
import utils.DataAndCounters
import utils.foldForTest
import utils.folderForTest
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
        val dataAndCounters = DataAndCounters()
        var resource: Resource<String, String> = Resource.Success(DataAndCounters.SUCCESS_DATA)

        assertTrue { resource is Resource.Success }
        assertTrue { resource.isSuccess }

        folderForTest(resource, dataAndCounters)
        foldForTest(resource, dataAndCounters)

        with(dataAndCounters) {
            assertTrue { loadingCount == 0 }
            assertTrue { noLoadingCount == 2 }
            assertTrue { successData == DataAndCounters.SUCCESS_DATA }
            assertTrue { successCount == 2 }
            assertTrue { noSuccessCount == 0 }
            assertTrue { errorData == DataAndCounters.NO_ERROR_DATA }
            assertTrue { errorCount == 0 }
            assertTrue { noErrorCount == 2 }
        }

        resource = Resource.Success(DataAndCounters.SUCCESS_DATA_2)

        folderForTest(resource, dataAndCounters)
        foldForTest(resource, dataAndCounters)
        ifFolding(resource, dataAndCounters)

        with(dataAndCounters) {
            assertTrue { loadingCount == 0 }
            assertTrue { noLoadingCount == 5 }
            assertTrue { successData == DataAndCounters.SUCCESS_DATA_2 }
            assertTrue { successCount == 5 }
            assertTrue { noSuccessCount == 0 }
            assertTrue { errorData == DataAndCounters.NO_ERROR_DATA }
            assertTrue { errorCount == 0 }
            assertTrue { noErrorCount == 5 }
        }
    }
}
