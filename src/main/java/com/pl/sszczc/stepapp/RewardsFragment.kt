package com.pl.sszczc.stepapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController

class RewardsFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_rewards, container, false)
        val buttonrepr = view.findViewById<Button>(R.id.buttonrepr)
        buttonrepr.setOnClickListener {
            findNavController().navigate(R.id.action_rewardsFragment_to_progressFragment)
        }
        return view
    }

}