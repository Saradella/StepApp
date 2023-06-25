package com.pl.sszczc.stepapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController

class StatisticsFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_statistics, container, false)
        val buttonstpr = view.findViewById<Button>(R.id.buttonstpr)
        buttonstpr.setOnClickListener {
            findNavController().navigate(R.id.action_statisticsFragment_to_progressFragment)
        }
        return view
    }


}