package id.ilhamsuaib.footballclub.data.local

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import id.ilhamsuaib.footballclub.data.local.entity.FavoriteEntity
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
    }

    override fun onCreate(db: SQLiteDatabase?) {
        createTableFavoriteMatch(db)
    }

    private fun createTableFavoriteMatch(db: SQLiteDatabase?) {
        db?.createTable(
                FavoriteEntity.FAVORITE_MATCH, true,
                FavoriteEntity.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
                FavoriteEntity.MATCH_ID to TEXT + UNIQUE,
                FavoriteEntity.HOME_TEAM_ID to TEXT,
                FavoriteEntity.AWAY_TEAM_ID to TEXT,
                FavoriteEntity.HOME_TEAM_NAME to TEXT,
                FavoriteEntity.AWAY_TEAM_NAME to TEXT,
                FavoriteEntity.HOME_SCORE to TEXT,
                FavoriteEntity.AWAY_SCORE to TEXT,
                FavoriteEntity.MATCH_DATE to TEXT
        )
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.dropTable(FavoriteEntity.FAVORITE_MATCH)
    }
}