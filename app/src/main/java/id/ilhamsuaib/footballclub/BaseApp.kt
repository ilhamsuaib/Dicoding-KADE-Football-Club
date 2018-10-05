package id.ilhamsuaib.footballclub

import android.app.Application
import id.ilhamsuaib.footballclub.data.ApiService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by @ilhamsuaib on 05/10/18.
 * github.com/ilhamsuaib
 */

class BaseApp : Application() {

    companion object {
        lateinit var API_SERVICE: ApiService
    }

    override fun onCreate() {
        super.onCreate()
        networkConfig()
    }

    private fun networkConfig() {
        val retrofit = Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(getHttpClient())
                .build()
        API_SERVICE = retrofit.create(ApiService::class.java)
    }

    private fun getHttpClient(): OkHttpClient {
        val defaultTimeOut = 30L
        return OkHttpClient.Builder()
                .connectTimeout(defaultTimeOut, TimeUnit.SECONDS)
                .writeTimeout(defaultTimeOut, TimeUnit.SECONDS)
                .readTimeout(defaultTimeOut, TimeUnit.SECONDS)
                .addInterceptor(HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                })
                .build()
    }
}