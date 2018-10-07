package id.ilhamsuaib.footballclub.ui.home.favorite

import id.ilhamsuaib.footballclub.base.BaseApp
import id.ilhamsuaib.footballclub.base.BasePresenter
import id.ilhamsuaib.footballclub.data.local.entity.FavoriteEntity
import id.ilhamsuaib.footballclub.utilities.logD
import id.ilhamsuaib.footballclub.utilities.toJson
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select

/**
 * Created by @ilhamsuaib on 08/10/18.
 * github.com/ilhamsuaib
 */

class FavoritePresenter : BasePresenter<ServiceCallback>() {

    fun getFavorites() {
        BaseApp.db?.use {
            val result = select(FavoriteEntity.FAVORITE_MATCH)
            val favorites = result.parseList(classParser<FavoriteEntity>())
            logD(FavoritesFragment.TAG, "favorites : ${favorites.toJson()}")
            val matchList = favorites.map {
                it.transform()
            }

            return@use callback?.showFavorites(matchList)
        }
    }
}