package com.rezazali.movieapp.presentation.ui.main.home


import android.content.ContentValues.TAG
import android.content.Context
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.util.Log

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.flexbox.*
import com.rezazali.movieapp.databinding.FragmentHomeBinding
import com.rezazali.movieapp.domain.utils.IdCategory
import com.rezazali.movieapp.presentation.ui.main.pager.BannerAdapter
import com.rezazali.movieapp.presentation.ui.main.pager.BannerViewModel


class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var owner: LifecycleOwner

    private lateinit var homeViewModel: HomeCategoryViewModel
    private lateinit var bannerViewModel: BannerViewModel
    private lateinit var allVideoViewModel: HomeAllVideoViewModel


    override fun onAttach(context: Context) {
        super.onAttach(context)
        owner = this
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment

        activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        binding = FragmentHomeBinding.inflate(layoutInflater)


        homeViewModel = ViewModelProvider(this)[HomeCategoryViewModel::class.java]

        bannerViewModel = ViewModelProvider(this)[BannerViewModel::class.java]

        allVideoViewModel = ViewModelProvider(this)[HomeAllVideoViewModel::class.java]

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        homeViewModel.getCategoryDrama(IdCategory.DRAMA.idWeb).observe(owner){t ->

            val adapter = MovieAdapter(requireActivity(),t.baseMovie)
            Log.d(TAG, "onViewCreated: ")
            binding.recyclerDrama.adapter = adapter

            setLayoutManager(binding.recyclerDrama)

        }

        homeViewModel.getCategoryScary(IdCategory.SCARY.idWeb).observe(owner){t ->

            val adapter = MovieAdapter(requireActivity(),t.baseMovie)
            Log.d(TAG, "onViewCreated: ")
            binding.recyclerScary.adapter = adapter

            binding.recyclerScary.let { setLayoutManager(it) }

        }

        bannerViewModel.getBanner().observe(owner){t ->

            val adapter = BannerAdapter(requireActivity(),t.listBanner)

            binding.viewPager.adapter = adapter

            binding.viewPager.let { binding.dotPagerIndicator.attachTo(it) }

            Log.d(TAG, "onViewCreated: ".plus(t.listBanner))

            binding.viewPager.rotationY = (180).toFloat()

        }

        allVideoViewModel.getAllVideo().observe(owner){t ->

            val adapter = MovieAdapter(requireActivity(),t.baseMovie)
            Log.d(TAG, "onViewCreated: ".plus(t.baseMovie))
            binding.recyclerAllVideo.adapter = adapter

            val layoutManager = FlexboxLayoutManager(context).apply {
                justifyContent = JustifyContent.SPACE_BETWEEN
                alignItems = AlignItems.CENTER
                flexDirection = FlexDirection.ROW
                flexWrap = FlexWrap.WRAP
            }


            binding.recyclerAllVideo.layoutManager = layoutManager


        }

    }

    private fun setLayoutManager(recyclerView: RecyclerView){

        val layoutManager = LinearLayoutManager(activity,LinearLayoutManager.HORIZONTAL,false)

        recyclerView.layoutManager = layoutManager
    }

}