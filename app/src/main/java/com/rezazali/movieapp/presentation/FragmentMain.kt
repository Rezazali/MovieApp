package com.rezazali.movieapp.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rezazali.movieapp.R
import com.rezazali.movieapp.databinding.FragmentMainBinding
import com.rezazali.movieapp.presentation.ui.main.home.HomeFragment
import com.rezazali.movieapp.presentation.ui.main.pager.TabsAdapter


class FragmentMain : Fragment() {

   lateinit var binding: FragmentMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment


        binding.bottomBar.setOnItemSelectedListener{ item ->
            when (item.itemId) {
                R.id.menu_home -> {
                    binding.pager.currentItem = 0
                    binding.bottomBar.menu.findItem(R.id.menu_home).isChecked = true
                }

            }
            return@setOnItemSelectedListener false
        }

        val listFragment : MutableList<Fragment> = ArrayList()
        listFragment.add(HomeFragment())
        val adapter = TabsAdapter(this@FragmentMain,listFragment)

        binding.pager.adapter = adapter












        return binding.root
    }

}