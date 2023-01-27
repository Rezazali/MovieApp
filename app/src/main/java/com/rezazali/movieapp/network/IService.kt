package com.rezazali.movieapp.network

import com.rezazali.movieapp.domain.models.BaseBannerMovie
import com.rezazali.movieapp.domain.models.BaseMovie
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Query


interface IService {


    @GET("api.php?All_videos")
    fun getAllVideo() :Observable<BaseMovie>

    @GET("api.php?home_banner")
    fun getBannerMovie() :Observable<BaseBannerMovie>

    @GET("api.php")
    fun getVideoByCategoryId(@Query("cat_id") id: Int) :Observable<BaseMovie>



}