package com.pl.sszczc.stepapp.database

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.pl.sszczc.stepapp.R

class NewStepsActivity : AppCompatActivity() {

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_steps)
        val editStepsView = findViewById<EditText>(R.id.edit_steps)

        val button = findViewById<Button>(R.id.button_save)
        button.setOnClickListener {
            val replyIntent = Intent()
            if (TextUtils.isEmpty(editStepsView.text)) {
                setResult(Activity.RESULT_CANCELED, replyIntent)
            } else {
                val steps = editStepsView.text.toString()
                replyIntent.putExtra(EXTRA_REPLY, steps)
                setResult(Activity.RESULT_OK, replyIntent)
            }
            finish()
        }
    }

    companion object {
        const val EXTRA_REPLY = "com.example.android.stepslistsql.REPLY"
    }
}