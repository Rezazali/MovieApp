package com.rezazali.movieapp.domain.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class BaseBannerMovie(
    @SerializedName("ALL_IN_ONE_VIDEO")
    @Expose
    val listBanner: List<BannerMovie>
)

data class BannerMovie(
    val banner_image: String,
    val banner_image_thumb: String,
    val banner_name: String,
    val banner_url: String,
    val id: String
)
