package study.anders.dk.challenge2

import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun showLocation(view: View) {
        val intent = Intent(Intent.ACTION_VIEW,
                Uri.parse("http://www.google.com"))
        startActivity(intent)

    }
}
