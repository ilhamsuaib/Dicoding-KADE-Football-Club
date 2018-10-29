package id.ilhamsuaib.footballclub.data.local

import android.content.Context
import android.database.sqlite.SQLiteConstraintException
import android.database.sqlite.SQLiteDatabase
import id.ilhamsuaib.footballclub.base.BaseApp
import id.ilhamsuaib.footballclub.data.local.entity.MatchEntity
import id.ilhamsuaib.footballclub.data.local.entity.TeamEntity
import id.ilhamsuaib.footballclub.model.Match
import id.ilhamsuaib.footballclub.model.Team
import org.jetbrains.anko.db.*

/**
 * Created by @ilhamsuaib on 08/10/18.
 * github.com/ilhamsuaib
 */

class DatabaseHelper(context: Context) : ManagedSQLiteOpenHelper(context,
        "football_app.db", null, 1) {

    companion object {
        private var instance: DatabaseHelper? = null

        @Synchronized
        fun getInstance(context: Context): DatabaseHelper? {
            if (instance == null) {
                instance = DatabaseHelper(context.applicationContext)
            }
            return instance
        }

        fun addFavMatch(match: Match,
                        onFailed: (e: SQLiteConstraintException) -> Unit = {},
                        onSuccess: () -> Unit) {
            try {
                BaseApp.db?.use {
                    insert(MatchEntity.FAVORITE_MATCH,
                            MatchEntity.MATCH_ID to match.matchId,
                            MatchEntity.HOME_TEAM_ID to match.homeTeamId,
                            MatchEntity.AWAY_TEAM_ID to match.awayTeamId,
                            MatchEntity.HOME_TEAM_NAME to match.homeTeamName,
                            MatchEntity.AWAY_TEAM_NAME to match.awayTeamName,
                            MatchEntity.HOME_SCORE to match.homeScore,
                            MatchEntity.AWAY_SCORE to match.awayScore,
                            MatchEntity.MATCH_DATE to match.matchDate,
                            MatchEntity.MATCH_TIME to match.strTime)
                }
                onSuccess()
            } catch (e: SQLiteConstraintException) {
                onFailed(e)
            }
        }

        fun addFavTeam(team: Team,
                       onFailed: (e: SQLiteConstraintException) -> Unit = {},
                       onSuccess: () -> Unit) {
            try {
                BaseApp.db?.use {
                    insert(TeamEntity.FAVORITE_TEAM,
                            TeamEntity.TEAM_ID to team.idTeam,
                            TeamEntity.TEAM_NAME to team.strTeam,
                            TeamEntity.TEAM_BADGE to team.strTeamBadge,
                            TeamEntity.FORMED_YEAR to team.intFormedYear,
                            TeamEntity.STADIUM to team.strStadium,
                            TeamEntity.DESCRIPTION_EN to team.strDescriptionEN)
                }
                onSuccess()
            } catch (e: SQLiteConstraintException) {
                onFailed(e)
            }
        }
    }

    override fun onCreate(db: SQLiteDatabase?) {
        createTableFavoriteMatch(db)
        createTableFavoriteTeam(db)
    }

    private fun createTableFavoriteTeam(db: SQLiteDatabase?) {
        db?.createTable(TeamEntity.FAVORITE_TEAM, true,
                TeamEntity.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
                TeamEntity.TEAM_ID to TEXT + UNIQUE,
                TeamEntity.TEAM_NAME to TEXT,
                TeamEntity.TEAM_BADGE to TEXT,
                TeamEntity.FORMED_YEAR to TEXT,
                TeamEntity.STADIUM to TEXT,
                TeamEntity.DESCRIPTION_EN to TEXT
        )
    }

    private fun createTableFavoriteMatch(db: SQLiteDatabase?) {
        db?.createTable(
                MatchEntity.FAVORITE_MATCH, true,
                MatchEntity.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
                MatchEntity.MATCH_ID to TEXT + UNIQUE,
                MatchEntity.HOME_TEAM_ID to TEXT,
                MatchEntity.AWAY_TEAM_ID to TEXT,
                MatchEntity.HOME_TEAM_NAME to TEXT,
                MatchEntity.AWAY_TEAM_NAME to TEXT,
                MatchEntity.HOME_SCORE to TEXT,
                MatchEntity.AWAY_SCORE to TEXT,
                MatchEntity.MATCH_DATE to TEXT,
                MatchEntity.MATCH_TIME to TEXT
        )
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.dropTable(MatchEntity.FAVORITE_MATCH)
        db?.dropTable(TeamEntity.FAVORITE_TEAM)
    }
}