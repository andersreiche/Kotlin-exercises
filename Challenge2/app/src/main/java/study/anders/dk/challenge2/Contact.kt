package study.anders.dk.challenge2

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.content.Intent
import android.net.Uri
import kotlinx.android.synthetic.main.activity_contact.*

class Contact : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact)

    }

    fun sendEmail(view: View) {
        val i = Intent(Intent.ACTION_SEND,
                Uri.parse("mailto:"))

        val to = "andersreiche@ggmail.com"
        val subject = studentNoEditText.text.toString()
        val message = messageEditText.text.toString()

        i.putExtra(Intent.EXTRA_EMAIL, to)
        i.putExtra(Intent.EXTRA_SUBJECT, subject)
        i.putExtra(Intent.EXTRA_TEXT, message)
        i.setType("message/rfc822")
        startActivity(Intent.createChooser(i, "Chose an email client from..."))
    }
}
