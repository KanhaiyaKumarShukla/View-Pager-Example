package com.example.inhindi

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class CategoryAdapter (val mContext:Context, fm: FragmentManager): FragmentPagerAdapter(fm) {

    override fun getCount(): Int {
        return 4
    }

    override fun getItem(position: Int): Fragment {
        when(position){
            0-> return AnimalFragment()
            1-> return FruitFragment()
            2->return FlowerFragment()
            else -> return CityFragment()
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> mContext.getString(R.string.animal)
            1 -> mContext.getString(R.string.fruit)
            2 -> mContext.getString(R.string.flower)
            else -> mContext.getString(R.string.city)
        }
    }
}