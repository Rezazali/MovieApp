package com.rezazali.movieapp.presentation.ui.main.home

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.cardview.widget.CardView
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rezazali.movieapp.R
import com.rezazali.movieapp.databinding.MovieRowBinding
import com.rezazali.movieapp.domain.models.Movie
import com.squareup.picasso.Picasso

class MovieAdapter(var context: Context, var listMovie: List<Movie>) :
    RecyclerView.Adapter<MovieAdapter.MovieVH>() {

    private lateinit var movieRowBinding: MovieRowBinding

    class MovieVH(movieRowBinding: MovieRowBinding) :
        RecyclerView.ViewHolder(movieRowBinding.root)



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieVH {

        movieRowBinding = DataBindingUtil.inflate(
            LayoutInflater.from(context),
            R.layout.movie_row,
            parent,
            false
        )
        val movieVH = MovieVH(movieRowBinding)
        return movieVH
    }


    override fun onBindViewHolder(holder: MovieVH, position: Int) {

        val movie : Movie = listMovie[position]
        movieRowBinding.movie = movie

       // notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return listMovie.size
    }

    companion object{

        @JvmStatic
        @BindingAdapter("movieImage")
        fun showImage(image : AppCompatImageView , url : String ) {
            Picasso.get()
                .load(Uri.parse(url))
                .placeholder(R.drawable.progress_animation)
                .into(image)


        }

        @JvmStatic
        @BindingAdapter("setTitle")
        fun setTitle(textView: AppCompatTextView,string: String){
            textView.text = string
        }

        @JvmStatic
        @BindingAdapter("ClickItem")
        fun click(cardView: CardView, movie: Movie) {
            cardView.setOnClickListener { view: View? ->

                //view?.findNavController()?.navigate(R.id.action_homeFragment_to_movieDetailFragment)
                val action = HomeFragmentDirections.actionHomeFragmentToMovieDetailFragment(movie)
                view?.findNavController()?.navigate(action)

            }
        }
    }


}