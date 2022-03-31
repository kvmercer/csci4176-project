package com.example.eyedroptracker.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.eyedroptracker.fragments.HomeFragment
import com.example.eyedroptracker.fragments.MedicationFragment
import com.example.eyedroptracker.fragments.ReminderFragment

class TabPageAdapter(activity: FragmentActivity, private val tabCount: Int): FragmentStateAdapter(activity) {
    override fun getItemCount(): Int = tabCount

    override fun createFragment(position: Int): Fragment {
        return  when (position)
        {
            0 -> HomeFragment()
            1 -> MedicationFragment()
            2 -> ReminderFragment()
            else -> HomeFragment()

        }
    }
}