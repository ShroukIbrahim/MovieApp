package com.moshrouk.movieapp.adapter



import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter


class TabAdapter (val fragments: ArrayList<Fragment>, val titles: ArrayList<String>, fragmentManager: FragmentManager
) : FragmentPagerAdapter(fragmentManager) {

    override fun getItem(position: Int) = fragments[position]

    override fun getPageTitle(position: Int) = titles[position]

    override fun getCount() = fragments.size
}

