package com.pl.sszczc.stepapp.database

import android.view.View
import androidx.activity.viewModels
import com.pl.sszczc.stepapp.R
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class DatabaseActivity : AppCompatActivity() {

    private val newStepsActivityRequestCode = 1
    private val StepsViewModel: StepsViewModel by viewModels {
        StepsViewModelFactory((application as StepsApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_database)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        val adapter = StepsListAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener {
            val intent = Intent(this@DatabaseActivity, NewStepsActivity::class.java)
            startActivityForResult(intent, newStepsActivityRequestCode)
        }
        StepsViewModel.allSteps.observe(this) { steps ->
            steps.let { adapter.submitList(it) }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, intentData: Intent?) {
        super.onActivityResult(requestCode, resultCode, intentData)

        if (requestCode == newStepsActivityRequestCode && resultCode == Activity.RESULT_OK) {
            intentData?.getStringExtra(NewStepsActivity.EXTRA_REPLY)?.let { reply ->
                val steps = Steps(reply)
                StepsViewModel.insert(steps)
            }
        } else {
            Toast.makeText(applicationContext, R.string.empty_not_saved, Toast.LENGTH_LONG).show()
        }
    }
}