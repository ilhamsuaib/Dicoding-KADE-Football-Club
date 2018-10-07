package id.ilhamsuaib.footballclub.base

import android.app.Application
import id.ilhamsuaib.footballclub.data.local.DatabaseHelper

/**
 * Created by @ilhamsuaib on 08/10/18.
 * github.com/ilhamsuaib
 */

class BaseApp : Application() {

    companion object {
        var db: DatabaseHelper? = null
    }

    override fun onCreate() {
        super.onCreate()
        db = DatabaseHelper.getInstance(applicationContext)
    }
}