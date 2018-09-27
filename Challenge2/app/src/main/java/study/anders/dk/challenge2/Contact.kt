package study.anders.dk.challenge2

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.content.Intent
import kotlinx.android.synthetic.main.activity_contact.*

class Contact : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact)

        fun sendEmail(view: View) {
            val subject = studentNoEditText.text.toString()
            val message = messageEditText.text.toString()

            val i = Intent(Intent.ACTION_SEND)
            val addressees = arrayOf("somemail@somewhere.com")
            i.putExtra(Intent.EXTRA_EMAIL, addressees)
            i.putExtra(Intent.EXTRA_SUBJECT, subject)
            i.putExtra(Intent.EXTRA_TEXT, message)
            i.setType("message/rfc822")
            startActivity(i)
        }
    }
}
