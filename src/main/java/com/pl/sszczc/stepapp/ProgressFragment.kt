package com.pl.sszczc.stepapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController


class ProgressFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_progress, container, false)
        val buttonprre = view.findViewById<Button>(R.id.buttonprre)
        buttonprre.setOnClickListener {
            findNavController().navigate(R.id.action_progressFragment_to_rewardsFragment)
        }

        val buttonprst = view.findViewById<Button>(R.id.buttonprst)
        buttonprst.setOnClickListener {
            findNavController().navigate(R.id.action_progressFragment_to_statisticsFragment)
        }

        return view
    }

}