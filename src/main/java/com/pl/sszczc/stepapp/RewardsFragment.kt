package com.pl.sszczc.stepapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class RewardsFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_rewards, container, false)
    }

}
interface RewardsUpdateListener {
    fun onRewardsUpdate(imageResId: Int)
}

private var rewardsUpdateListener: RewardsUpdateListener? = null

fun setRewardsUpdateListener(listener: RewardsUpdateListener) {
    rewardsUpdateListener = listener
}