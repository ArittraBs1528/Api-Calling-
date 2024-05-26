package com.example.apicalling.network.Api

import android.provider.SyncStateContract
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.OkHttpClient
import com.example.apicalling.utilities.HeaderConstant
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient {

    companion object {
        private fun buildClient(): OkHttpClient {
            return OkHttpClient.Builder().addInterceptor(HttpLoggingInterceptor().apply {
                this.level = HttpLoggingInterceptor.Level.BODY
            }).addInterceptor { chain ->
                val request = chain.request().newBuilder()
                    .addHeader("Content-Type", HeaderConstant.CONTENT_TYPE)
                    .addHeader("DeviceId", HeaderConstant.DEVICE_ID)
                    .addHeader(
                        "NST", HeaderConstant.NST
                    )
                    .addHeader("User-Agent", HeaderConstant.USER_AGENT).build()

                chain.proceed(request)
            }.build()
        }

        fun getRetrofit(): Retrofit {
            return Retrofit.Builder().baseUrl(HeaderConstant.BASE_URL).client(buildClient())
                .addConverterFactory(GsonConverterFactory.create()).build()
        }
    }

    //making our retrofit

}