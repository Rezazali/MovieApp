package com.rezazali.movieapp.network

import com.rezazali.movieapp.util.Constants
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {

    @Volatile
    private var retrofit : Retrofit? = null

    fun getInstance() :Retrofit{

        if (retrofit == null){
            synchronized(ApiClient::class.java){
                if (retrofit ==null){
                    retrofit = Retrofit.Builder()
                        .baseUrl(Constants.BASE_URL)
                        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()
                }
            }
        }

        return retrofit!!
    }
}