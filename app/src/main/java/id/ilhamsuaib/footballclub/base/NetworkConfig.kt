package id.ilhamsuaib.footballclub.base

import id.ilhamsuaib.footballclub.BuildConfig
import id.ilhamsuaib.footballclub.data.remote.ApiService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by @ilhamsuaib on 05/10/18.
 * github.com/ilhamsuaib
 */

class NetworkConfig {

    companion object {
        fun getApiService(): ApiService = NetworkConfig().getRetrofit().create(ApiService::class.java)
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(getHttpClient())
                .build()
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