package id.ilhamsuaib.footballclub.ui.home.match

import id.ilhamsuaib.footballclub.data.Repository
import id.ilhamsuaib.footballclub.model.Match
import io.reactivex.Single
import io.reactivex.schedulers.TestScheduler
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.never
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations


class MatchPresenterTest {

    @Mock
    lateinit var repo: Repository

    @Mock
    lateinit var callback: ServiceCallback

    private lateinit var presenter: MatchPresenter

    @Before
    fun before() {
        MockitoAnnotations.initMocks(this)
        val testScheduler = TestScheduler()
        presenter = MatchPresenter(repo, testScheduler, testScheduler)
    }

    @Test
    fun `fetch valid data should load into view`() {
        val matchList = listOf<Match>()
        val matchType = "eventspastleague.php"
        val leagueId = "4335"

        //given
        presenter.bindCallback(callback)

        //when
        Mockito.`when`(repo.getMatch(matchType, leagueId))
                .thenReturn(Single.just(matchList))

        //then
        presenter.getMatch(matchType, leagueId)

        //verify
        verify(callback, never()).showMatch(matchList)
    }
}