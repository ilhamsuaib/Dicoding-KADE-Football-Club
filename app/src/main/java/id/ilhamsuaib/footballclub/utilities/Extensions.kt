package id.ilhamsuaib.footballclub.utilities

import android.content.Context
import android.util.Log
import android.widget.ImageView
import com.google.gson.Gson
import com.google.gson.JsonElement
import com.squareup.picasso.Picasso
import id.ilhamsuaib.footballclub.data.local.DatabaseHelper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by @ilhamsuaib on 05/10/18.
 * github.com/ilhamsuaib
 */

fun <T> Call<T>.getResponse(onFailure: (message: String) -> Unit = {},
                            onResponse: (response: T?) -> Unit) {
    this.enqueue(object : Callback<T> {
        override fun onFailure(call: Call<T>, t: Throwable) {
            t.printStackTrace()
            t.message?.let { onFailure(it) }
        }

        override fun onResponse(call: Call<T>, response: Response<T>) {
            if (200 == response.code()) {
                onResponse(response.body())
            } else {
                onFailure("Maaf, terjadi kesalahan saat membaca response dari server")
            }
        }
    })
}

fun Any?.toJson(): JsonElement = Gson().toJsonTree(this)

fun ImageView.loadImage(url: String?) {
    logD(s = "image url : $url")
    Picasso.get().load(url).into(this)
}

fun String.parseDate(pattern: String): String {
    logD(s = "old date $this")
    val sdf = SimpleDateFormat(pattern, Locale.getDefault())
    val date = sdf.parse(this)
    logD(s = "old date $date")
    val newSdf = SimpleDateFormat("E, dd MMM yyyy", Locale.getDefault())
    return newSdf.format(date)
}

val Context.database: DatabaseHelper?
    get() = DatabaseHelper.getInstance(applicationContext)

fun logD(tag: String = "logD", s: String?) {
    Log.d(tag, s)
}