package study.anders.dk.simplehiit

import android.os.Bundle
import android.os.CountDownTimer
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity;
import android.view.Menu
import android.view.MenuItem
import android.view.View

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    var countDownTimer = object : CountDownTimer(20000, 10) {
        override fun onTick(millisUntilFinished: Long) {
            val timer = millisUntilFinished/1000
            textView.text = timer.toString()
        }

        override fun onFinish() {
            textView.text = "Done"
        }
    }

    fun clickedTimer(view: View) {
        countDownTimer.start()

    }
}


