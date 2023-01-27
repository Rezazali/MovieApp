package com.rezazali.movieapp.presentation.ui.player

import android.content.pm.ActivityInfo
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.Player
import com.rezazali.movieapp.R
import com.rezazali.movieapp.databinding.FragmentMoviePlayerBinding


class MoviePlayerFragment : Fragment() {

    private val args by navArgs<MoviePlayerFragmentArgs>()
    lateinit var  fullScreen : AppCompatImageView
    lateinit var  lockScreen : AppCompatImageView
    lateinit var  simpleExoPlayer : ExoPlayer

    private var isFullScreen = false

    var isLock = false
    lateinit var binding : FragmentMoviePlayerBinding
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentMoviePlayerBinding.inflate(layoutInflater)

        activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        lockScreen = view.findViewById(R.id.exo_lock)


        lockScreen.setOnClickListener {


            if (!isLock){

                lockScreen.setImageDrawable(ContextCompat.getDrawable(requireActivity(),R.drawable.ic_baseline_lock))

            }
            else
            {
                lockScreen.setImageDrawable(ContextCompat.getDrawable(requireActivity(),R.drawable.ic_baseline_lock_open_24))
            }

            isLock = !isLock
            lockScreen(isLock)
        }




         simpleExoPlayer = ExoPlayer.Builder(requireContext())
            .setSeekBackIncrementMs(5000)
            .setSeekForwardIncrementMs(5000)
            .build()

        binding.exoPlayer.player = simpleExoPlayer
        binding.exoPlayer.keepScreenOn = true



        simpleExoPlayer.addListener(object : Player.Listener {
            override fun onPlaybackStateChanged(@Player.State state: Int) {
                if (state == Player.STATE_BUFFERING) {

                    binding.progressBar.visibility = View.VISIBLE

                } else if(state == Player.STATE_READY) {

                    binding.progressBar.visibility = View.GONE

                }
            }
        })

        val madiaItem = MediaItem.fromUri(Uri.parse(args.playMovieId.video_url))
        simpleExoPlayer.setMediaItem(madiaItem)
        simpleExoPlayer.prepare()
        simpleExoPlayer.play()





    }

    private fun lockScreen(lock: Boolean) {
        val layoutContainer1 = view?.findViewById<LinearLayout>(R.id.sec_controlvidi)
        val layoutContainer2 = view?.findViewById<LinearLayout>(R.id.sec_controlvid2)

        if (lock){
            layoutContainer1?.visibility = View.INVISIBLE
            layoutContainer2?.visibility = View.INVISIBLE
        }
        else
        {
            layoutContainer1?.visibility = View.VISIBLE
            layoutContainer2?.visibility = View.VISIBLE
        }

        requireActivity().onBackPressedDispatcher.apply {
            if (isLock) return
            if(resources.configuration.orientation == android.content.res.Configuration.ORIENTATION_LANDSCAPE){
                fullScreen.performClick()
            }
        }
    }

    override fun onStop() {
        super.onStop()
        simpleExoPlayer.stop()
    }

    override fun onDestroy() {
        super.onDestroy()
        simpleExoPlayer.release()
    }

    override fun onPause() {
        super.onPause()
        simpleExoPlayer.pause()
    }


}