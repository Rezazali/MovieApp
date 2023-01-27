package com.rezazali.movieapp.presentation.ui.movieDetail

import android.content.ContentValues.TAG
import android.content.pm.ActivityInfo
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.text.Html
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.text.HtmlCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.rezazali.movieapp.R
import com.rezazali.movieapp.databinding.FragmentMovieDetailBinding
import com.squareup.picasso.Picasso


class MovieDetailFragment : Fragment() {

    private val args by navArgs<MovieDetailFragmentArgs>()
    lateinit var  binding :FragmentMovieDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentMovieDetailBinding.inflate(layoutInflater)

        loadImage()

        textHtmlFormater()

        activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        //back state image
        binding.imgBack.setOnClickListener { findNavController().popBackStack() }

        //intent to player movie
        binding.btnPlayer.setOnClickListener {
            val action = MovieDetailFragmentDirections.actionMovieDetailFragmentToMoviePlayerFragment(args.movieDetail)
            findNavController().navigate(action)
        }



        return binding.root
    }

    private fun loadImage(){
        activity?.let { Picasso.get()
            .load(Uri.parse(args.movieDetail.video_thumbnail_b))
            .placeholder(R.drawable.progress_animation)
            .into(binding.imageMovie) }
    }


    @RequiresApi(Build.VERSION_CODES.N)
    private fun textHtmlFormater(){
        val htmlAsSpanned = Html.fromHtml(args.movieDetail.video_description, HtmlCompat.FROM_HTML_MODE_LEGACY)

        binding.txtDescription.text = htmlAsSpanned

        this.setText()
    }

    private fun setText(){
        binding.txtTitle.text = args.movieDetail.video_title

        binding.txtAvg.text = args.movieDetail.rate_avg

        binding.txtMinute.text = args.movieDetail.video_duration
    }


}