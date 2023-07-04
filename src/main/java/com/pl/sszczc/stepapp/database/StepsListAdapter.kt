package com.pl.sszczc.stepapp.database

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.pl.sszczc.stepapp.R

class StepsListAdapter : ListAdapter <Steps, StepsListAdapter.StepsViewHolder>(STEPS_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StepsViewHolder {
        return StepsViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: StepsViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current.steps)
    }

    class StepsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val stepsItemView: TextView = itemView.findViewById(R.id.textView)

        fun bind(text: String?) {
            stepsItemView.text = text
        }

        companion object {
            fun create(parent: ViewGroup): StepsViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.recyclerview_item, parent, false)
                return StepsViewHolder(view)
            }
        }
    }

    companion object {
        private val STEPS_COMPARATOR = object : DiffUtil.ItemCallback<Steps>() {
            override fun areItemsTheSame(oldItem: Steps, newItem: Steps): Boolean {
                return oldItem === newItem
            }

            override fun areContentsTheSame(oldItem: Steps, newItem: Steps): Boolean {
                return oldItem.steps == newItem.steps
            }
        }
    }
}