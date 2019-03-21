package com.lambreta.rendafixa.core.platform

import android.content.Context
import com.lambreta.rendafixa.core.extension.networkInfo
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import timber.log.Timber
import java.util.concurrent.TimeUnit

/**
 * Base network module to be used to inject particular retrofit instance
 */
class WebServiceHandler {

    private val cacheDuration: Long = 5
    private val staleDuration: Long = 60*60*24*7
    private val cacheSize: Long = 5*1024*1024

    inline fun <reified T> createService(url: String, context: Context): T {
        val httpClient = createOkHttpClient(context)
        return createRetrofit(url, httpClient).create(T::class.java)
    }

    fun createRetrofit(url: String, httpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(url)
            .client(httpClient)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    fun createOkHttpClient(context: Context): OkHttpClient =
        OkHttpClient.Builder()
            .connectTimeout(60L, TimeUnit.SECONDS)
            .readTimeout(60L, TimeUnit.SECONDS)
            .addInterceptor(logInterceptor())
            .addInterceptor(cacheInterceptor(context))
            .cache(Cache(context.cacheDir, cacheSize))
            .build()

    private fun logInterceptor() =
        HttpLoggingInterceptor(HttpLoggingInterceptor.Logger {
            Timber.tag("OkHttp").d(it)
        }).apply {
            level = HttpLoggingInterceptor.Level.BASIC
        }

    private fun cacheInterceptor(context: Context) =
        Interceptor { chain ->
            var request = chain.request()
            request = when (context.networkInfo?.isConnected) {
                true -> request.newBuilder()
                    .header("Cache-Control", "public, max-age=$cacheDuration")
                    .build()
                else -> request.newBuilder()
                    .header("Cache-Control", "public, only-if-cached, max-stale=$staleDuration")
                    .build()
            }
            chain.proceed(request)
        }
}


