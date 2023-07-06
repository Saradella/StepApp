package com.pl.sszczc.stepapp.progress
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.mikhaellopez.circularprogressbar.CircularProgressBar
import com.pl.sszczc.stepapp.R


class StepGoal : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_step_goal)
        supportActionBar?.setDisplayShowTitleEnabled(false)
    }


    fun saveButtonClicked(view: View?) {
        val editText: EditText = findViewById(R.id.edit_step_goal)
        val stepGoalText = editText.text.toString()
        if (!stepGoalText.isEmpty()) {
            val stepGoal = stepGoalText.toInt()

            val resultIntent = Intent()
            resultIntent.putExtra("stepGoal", stepGoal)
            setResult(Activity.RESULT_OK, resultIntent)
            finish()
        }
    }
}
