package com.ibrahim.carstask.network

import com.ibrahim.carstask.utils.Constant
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object APIClient {
    private var servicesApiInterface: ServicesApiInterface? = null

    fun build(): ServicesApiInterface? {


        val builder: Retrofit.Builder = Retrofit.Builder()
            .baseUrl(Constant.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())


        val httpClient: OkHttpClient.Builder = OkHttpClient.Builder()

        httpClient
            .connectTimeout(2, TimeUnit.MINUTES)
            .readTimeout(2, TimeUnit.MINUTES)
            .writeTimeout(2, TimeUnit.MINUTES)


        val retrofit: Retrofit = builder.client(httpClient.build()).build()


        servicesApiInterface = retrofit.create(
            ServicesApiInterface::class.java
        )

        return servicesApiInterface as ServicesApiInterface
    } // build

} // class of APIClient