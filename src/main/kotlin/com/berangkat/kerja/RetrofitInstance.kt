package com.berangkat.kerja

import com.berangkat.kerja.model.OpenCageData
import io.reactivex.Flowable
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RetrofitInstance {

    @GET("/geocode/v1/json")
    fun getLocation(
            @Query("q") name: String,
            @Query("key") token: String
    ): Call<OpenCageData>

    @GET("/geocode/v1/json")
    fun getLocationRx(
            @Query("q") name: String,
            @Query("key") token: String
    ): Flowable<OpenCageData>

    companion object {
        fun create(): RetrofitInstance {
            val retrofit = Retrofit.Builder()
                    .baseUrl("https://api.opencagedata.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

            return retrofit.create(RetrofitInstance::class.java)
        }
    }
}
