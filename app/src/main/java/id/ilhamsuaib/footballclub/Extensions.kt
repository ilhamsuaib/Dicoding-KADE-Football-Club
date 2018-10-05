package id.ilhamsuaib.footballclub

import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by @ilhamsuaib on 05/10/18.
 * github.com/ilhamsuaib
 */

fun <T> Call<T>.getResponse(onResponse: (response: T?) -> Unit,
                            onFailure: (message: String) -> Unit) {
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

fun Any.toJson() = Gson().toJsonTree(this)