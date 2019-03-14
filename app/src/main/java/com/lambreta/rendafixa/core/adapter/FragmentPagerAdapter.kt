package com.lambreta.rendafixa.core.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter as AndroidFragmentPagerAdapter

class FragmentPagerAdapter(fragmentManager: FragmentManager,
                           var fragments: List<Fragment>,
                           var titles: List<String>) : AndroidFragmentPagerAdapter(fragmentManager) {

    override fun getItem(position: Int): Fragment {
        return fragments[position]
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return titles[position]
    }

    override fun getCount(): Int {
        return fragments.size
    }
}