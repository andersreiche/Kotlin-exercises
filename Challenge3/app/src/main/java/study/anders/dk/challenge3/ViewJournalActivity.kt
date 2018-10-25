package study.anders.dk.challenge3

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.Toolbar
import kotlinx.android.synthetic.main.activity_view_journal.*

class ViewJournalActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_journal)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        toolbar.setNavigationOnClickListener { view ->
            finish()
        }

        val extras = intent.extras ?: return
        val title = extras.get("viewTitle")
        val description = extras.get("viewDescription")

        titleText.text = title.toString()
        descriptionText.text = description.toString()
    }
}
