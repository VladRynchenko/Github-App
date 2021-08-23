package com.example.githubapp.repos

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.paging.AsyncPagingDataDiffer
import androidx.paging.PagingData
import androidx.recyclerview.widget.ListUpdateCallback
import com.example.githubapp.models.DataItem
import com.example.githubapp.models.Repos
import com.example.githubapp.models.UserData
import com.example.githubapp.repository.GithubRepository
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeoutException

@ExperimentalCoroutinesApi
class ReposViewModelTest {
    @Rule
    @JvmField
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val testDispatcher = TestCoroutineDispatcher()
    private val testScope = TestCoroutineScope(testDispatcher)

    lateinit var repository: GithubRepository
    lateinit var viewModel: ReposViewModel
    private val userData =
        UserData("login", null, null, 0, null, null, null, null, null, null, null, null)
    private val repo = Repos("repo", 0, "repo", null, "url", userData, 0, 0, null)
    private val repoList: List<DataItem> = listOf(repo, repo)

    @Before
    fun setUp() {
        repository = Mockito.mock(GithubRepository::class.java)
        viewModel = ReposViewModel(repository)
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }


    @Test
    fun searchRepo() = runBlockingTest {
        `when`(repository.getSearchResultStream("query")).thenReturn(
            flow {
                emit(PagingData.from(repoList))
            }
        )
        viewModel.searchRepo("query")
        val differ = AsyncPagingDataDiffer(
            diffCallback = diffUtil,
            updateCallback = NoopListCallback(),
            workerDispatcher = Dispatchers.Main
        )
        differ.submitData(viewModel.data.getOrAwaitValue())
        advanceUntilIdle()
        assertEquals(repoList, differ.snapshot().items)
    }
}

fun <T> LiveData<T>.getOrAwaitValue(
    time: Long = 2,
    timeUnit: TimeUnit = TimeUnit.SECONDS
): T {
    var data: T? = null
    val latch = CountDownLatch(1)
    val observer = object : Observer<T> {
        override fun onChanged(o: T?) {
            data = o
            latch.countDown()
            this@getOrAwaitValue.removeObserver(this)
        }
    }

    this.observeForever(observer)

    // Don't wait indefinitely if the LiveData is not set.
    if (!latch.await(time, timeUnit)) {
        throw TimeoutException("LiveData value was never set.")
    }

    @Suppress("UNCHECKED_CAST")
    return data as T
}

class NoopListCallback : ListUpdateCallback {
    override fun onChanged(position: Int, count: Int, payload: Any?) {}
    override fun onMoved(fromPosition: Int, toPosition: Int) {}
    override fun onInserted(position: Int, count: Int) {}
    override fun onRemoved(position: Int, count: Int) {}
}