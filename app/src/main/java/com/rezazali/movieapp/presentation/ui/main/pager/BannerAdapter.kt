package com.rezazali.movieapp.presentation.ui.main.pager

import android.content.Context
import android.icu.number.NumberFormatter.with
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide
import com.rezazali.movieapp.R
import com.rezazali.movieapp.domain.models.BannerMovie
import com.squareup.picasso.Picasso


class BannerAdapter (var context: Context, var newsList: List<BannerMovie>) : PagerAdapter(){

    override fun getCount(): Int {
        return newsList.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return  view.equals(`object`)
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View?)
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {

        val view: View = LayoutInflater.from(context).inflate(R.layout.image_layout, null)

        val image = view.findViewById<AppCompatImageView>(R.id.image)

        container.addView(view, 0)



        val banner: BannerMovie = newsList[position]
        Picasso.get()
            .load(Uri.parse(banner.banner_image))
            .placeholder(R.drawable.progress_animation)
            .into(image)




        return view
    }
}