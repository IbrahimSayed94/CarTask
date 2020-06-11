package com.ibrahim.carstask.network

import com.ibrahim.carstask.data.response.CarResponse
import io.reactivex.Observable
import retrofit2.http.GET

interface ServicesApiInterface
{
    @GET("api/index/make=all.json")
    fun getCarList(): Observable<CarResponse>
}