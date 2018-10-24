package study.anders.dk.challenge3

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar
import android.view.*
import android.widget.Toast

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var dataArray : java.util.ArrayList<JournalEntry>
    lateinit var journalAdapter: JournalListAdapter
    lateinit var prefenceManager : JournalPreferenceManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            showAddJournal()
        }

        prefenceManager = JournalPreferenceManager(this)

        //Fetch the dataArray
        dataArray = prefenceManager.getSavedJournals()

        //Create the custom Array Adapter
        journalAdapter = JournalListAdapter(this, 0, dataArray)

        //Set adapter of the ListView
        main_listview.adapter = journalAdapter

        //Grab data from AddJournalActivity and add to the list
        val extras = intent.extras ?: return
        val title = extras.get("Title")
        val description = extras.get("Description")
        val newEntry = JournalEntry(title.toString(), description.toString())
        prefenceManager.saveJournal(newEntry)
        updateListView()

    }

    fun updateListView() {
        dataArray = prefenceManager.getSavedJournals()
        journalAdapter.clear()
        journalAdapter.addAll(dataArray)
        journalAdapter.notifyDataSetChanged()
    }

    fun showAddJournal() {
        val i = Intent(this, AddJournalActivity::class.java)
        startActivity(i)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.main_menu_save -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
