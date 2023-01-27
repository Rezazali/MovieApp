package com.rezazali.movieapp.domain.models

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


data class BaseMovie(
    @SerializedName("ALL_IN_ONE_VIDEO")
    @Expose
    val baseMovie: List<Movie>
)
@Parcelize
data class Movie(
    @SerializedName("cat_id")
    @Expose
    val cat_id: String,
    @SerializedName("category_image")
    @Expose
    val category_image: String,
    @SerializedName("category_image_thumb")
    @Expose
    val category_image_thumb: String,
    @SerializedName("category_name")
    @Expose
    val category_name: String,
    @SerializedName("cid")
    @Expose
    val cid: String,
    @SerializedName("id")
    @Expose
    val id: String,
    @SerializedName("rate_avg")
    @Expose
    val rate_avg: String,
    @SerializedName("totel_viewer")
    @Expose
    val totel_viewer: String,
    @SerializedName("video_description")
    @Expose
    val video_description: String,
    @SerializedName("video_duration")
    @Expose
    val video_duration: String,
    @SerializedName("video_id")
    @Expose
    val video_id: String,
    @SerializedName("video_thumbnail_b")
    @Expose
    val video_thumbnail_b: String,
    @SerializedName("video_thumbnail_s")
    @Expose
    val video_thumbnail_s: String,
    @SerializedName("video_title")
    @Expose
    val video_title: String,
    @SerializedName("video_type")
    @Expose
    val video_type: String,
    @SerializedName("video_url")
    @Expose
    val video_url: String
) : Parcelable

