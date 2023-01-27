package com.rezazali.movieapp.presentation.ui.main.pager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.rezazali.movieapp.presentation.ui.main.home.HomeFragment

class TabsAdapter(fragment: Fragment, private val list: List<Fragment>): FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int {
        return list.size
    }

    override fun createFragment(position: Int): Fragment {
        return list[position]
    }
}