package study.anders.dk.challenge3

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_add_journal.*

class AddJournalActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_journal)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        toolbar.setNavigationOnClickListener { view ->
            finish()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_add, menu)
        return true
    }

    fun saveJournal() {
        val i = Intent()
        i.putExtra("Title", editTitle.text.toString())
        i.putExtra("Description", editDescription.text.toString())
        i.putExtra("Timestamp", (System.currentTimeMillis()/1000).toString())
        setResult(Activity.RESULT_OK, i)
        finish()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.main_menu_save -> {
                Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show()
                saveJournal()

                return true
            }
            else -> return true
        }
    }
}
