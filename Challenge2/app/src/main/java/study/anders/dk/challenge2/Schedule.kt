package study.anders.dk.challenge2

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_schedule.*

class Schedule : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_schedule)

        val extras = intent.extras ?: return

        val Day = extras.get("Day")
        dayText.text = Day.toString()

        when (Day) {
            "Monday" -> {
                subjectText1.text = "ASU"
                subjectText2.text = ""
                subjectText3.text = ""
                subjectText4.text = ""
            }
            "Tuesday" -> {
                subjectText1.text = "BDA"
                subjectText2.text = "IOT"
                subjectText3.text = ""
                subjectText4.text = "PME"
            }
            "Wednesday" -> {
                subjectText1.text = ""
                subjectText2.text = ""
                subjectText3.text = "IOT"
                subjectText4.text = ""
            }
            "Thursday" -> {
                subjectText1.text = "BDA"
                subjectText2.text = ""
                subjectText3.text = "ASU"
                subjectText4.text = ""
            }
            "Friday" -> {
                subjectText1.text = ""
                subjectText2.text = ""
                subjectText3.text = ""
                subjectText4.text = ""
            }
        }
    }
}
