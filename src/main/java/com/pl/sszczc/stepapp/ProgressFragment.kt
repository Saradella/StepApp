package com.pl.sszczc.stepapp

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.pl.sszczc.stepapp.progress.StepGoal

private const val STEP_GOAL_REQUEST_CODE = 100
private const val SHARED_PREFS_NAME = "MyPrefs"
private const val STEP_GOAL_KEY = "stepGoal"

class ProgressFragment : Fragment() {
    private lateinit var stepsDailyTextView: TextView
    private lateinit var sharedPreferences: SharedPreferences


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {








        val view = inflater.inflate(R.layout.fragment_progress, container, false)


        //obliczanie spalonych kalorii i kilometrow
        val text_calorie_burned = view.findViewById<TextView>(R.id.text_calorie_burned)
        val steps = view.findViewById<TextView>(R.id.steps_current)
        val stepsText = steps.text.toString()
        val currentSteps = stepsText.toInt()
        val calories_burned = currentSteps * 0.04
        text_calorie_burned.text = String.format("%d kcal", calories_burned.toInt())

        val text_km = view.findViewById<TextView>(R.id.text_distance_travelled)
        val KilometersMade = currentSteps * 0.00085
        text_km.text = String.format("%.2f km", KilometersMade)

        //warunek ilosc krokow = cel krokow daje +1 do nagrod







        stepsDailyTextView = view.findViewById(R.id.steps_daily)
        sharedPreferences = requireContext().getSharedPreferences(SHARED_PREFS_NAME, Context.MODE_PRIVATE)

        // Pobierz domyślną wartość zasobu string
        val defaultStepGoal = resources.getInteger(R.integer.default_step_goal)

        // Sprawdź, czy wartość celu kroków jest dostępna w SharedPreferences
        val currentStepGoal = sharedPreferences.getInt(STEP_GOAL_KEY, defaultStepGoal)

        // Ustaw wartość celu kroków jako tekst daily_steps
        val stepsDailyText = getString(R.string.daily_steps, currentStepGoal)
        stepsDailyTextView.text = stepsDailyText

        stepsDailyTextView.setOnClickListener {
            val intent = Intent(requireContext(), StepGoal::class.java)
            startActivityForResult(intent, STEP_GOAL_REQUEST_CODE)
        }

        return view
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == STEP_GOAL_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            val stepGoal = data?.getIntExtra("stepGoal", 0)
            if (stepGoal != null) {
                // Zapisz wartość celu kroków w SharedPreferences
                val editor = sharedPreferences.edit()
                editor.putInt(STEP_GOAL_KEY, stepGoal)
                editor.apply()

                val stepsDailyText = getString(R.string.daily_steps, stepGoal)
                stepsDailyTextView.text = stepsDailyText
            }
        }
    }
}