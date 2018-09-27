package study.anders.dk.challenge2

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.RadioGroup

class MainActivity : AppCompatActivity() {

    var Day = "Monday"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val radioGroup = findViewById<RadioGroup>(R.id.radioGroupDays)
        radioGroup?.setOnCheckedChangeListener { _, checkedId ->

            when (checkedId) {
                R.id.radioButtonMon -> Day = "Monday"
                R.id.radioButtonTue -> Day = "Tuesday"
                R.id.radioButtonWed -> Day = "Wednesday"
                R.id.radioButtonThu -> Day = "Thursday"
                R.id.radioButtonFri -> Day = "Friday"
            }
        }
    }

    fun showSchedule(view: View) {
        val i = Intent(this, Schedule::class.java)
        i.putExtra("Day", Day)
        startActivity(i)
    }

    fun showLocation(view: View) {
        val i = Intent(Intent.ACTION_VIEW,
                Uri.parse("geo:56.129457, 9.028489"))
        startActivity(i)
    }

    fun showContactPage (view: View) {
        val i = Intent(this, Contact::class.java)
        startActivity(i)
    }
}
