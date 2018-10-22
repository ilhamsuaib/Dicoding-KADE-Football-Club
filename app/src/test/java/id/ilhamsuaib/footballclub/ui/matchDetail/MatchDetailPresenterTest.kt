package id.ilhamsuaib.footballclub.ui.matchDetail

import id.ilhamsuaib.footballclub.data.Repository
import id.ilhamsuaib.footballclub.data.remote.model.MatchModel
import io.reactivex.Observable
import io.reactivex.schedulers.TestScheduler
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

class MatchDetailPresenterTest {

    @Mock
    lateinit var repo: Repository

    @Mock
    lateinit var callback: ServiceCallback

    @Mock
    lateinit var matchModel: MatchModel

    private lateinit var presenter: MatchDetailPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        val testScheduler = TestScheduler()
        presenter = MatchDetailPresenter(repo, testScheduler, testScheduler)
    }

    @Test
    fun `fetch valid data should load into view`() {
        val matchList = emptyList<MatchModel>()
        val matchId = "584435"

        //given
        presenter.bindCallback(callback)

        //when
        `when`(repo.getMatchDetail(matchId)).thenReturn(Observable.just(matchList))

        //then
        presenter.getMatchDetail(matchId)

        //verify
        Mockito.verify(callback, Mockito.never()).showMatchDetail(matchModel)
    }

    @Test
    fun `fetch error should return error to view`() {
        val exception = Exception()
        val matchId = "584435"

        //given
        presenter.bindCallback(callback)

        //when
        `when`(repo.getMatchDetail(matchId)).thenReturn(Observable.error(exception))

        //then
        presenter.getMatchDetail(matchId)

        //verify
        Mockito.verify(callback, never()).onFailed("error message")
    }
}