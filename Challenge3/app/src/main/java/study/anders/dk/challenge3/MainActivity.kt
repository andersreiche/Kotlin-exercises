package study.anders.dk.challenge3

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar
import android.view.*
import android.widget.AdapterView
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

        //Click to view list entry, long click to remove list entry
        onListClick()
        onLongListClick()
        
        prefenceManager = JournalPreferenceManager(this)

        //Fetch the dataArray
        dataArray = prefenceManager.getSavedJournals()

        //Create the custom Array Adapter
        journalAdapter = JournalListAdapter(this, 0, dataArray)

        //Set adapter of the ListView
        main_listview.adapter = journalAdapter
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 90) {
            if (resultCode == Activity.RESULT_OK) {

                //Grab data from AddJournalActivity and add to the list
                val title = data?.getStringExtra("Title")
                val description = data?.getStringExtra("Description")
                val extraTime = data?.getStringExtra("Timestamp")
                val newEntry = JournalEntry(title.toString(), description.toString(), extraTime.toString())
                prefenceManager.saveJournal(newEntry)
                updateListView()
            }
        }
    }

    fun onListClick() {
        main_listview.setOnItemClickListener {
                parent: AdapterView<*>?, view: View?, position: Int, id: Long ->
            val extraTitle = dataArray[position].title
            val extraDescription = dataArray[position].description
            val i = Intent(this, ViewJournalActivity::class.java)
            i.putExtra("viewTitle", extraTitle)
            i.putExtra("viewDescription", extraDescription)
            startActivity(i)
        }
    }

    fun onLongListClick() {
        main_listview.setOnItemLongClickListener { parent, view, position, id ->
            val alert = AlertDialog.Builder(this)
            alert.setTitle(getString(R.string.alertDeleteConfirmationTitle))
            alert.setMessage(getString(R.string.alertDeleteConfirmationText))
            alert.setPositiveButton(getString(R.string.alertSetPositiveButtonText)) { dialog, which ->
                //Delete list entry
                prefenceManager.removeJournal(dataArray[position], dataArray)
                dataArray.remove(dataArray[position])
                updateListView()
                //journalAdapter.notifyDataSetChanged()
            }
            alert.setNegativeButton(getString(R.string.alertSetNegativeButtonText)){ dialog, which ->
                //Do nothing
            }
            val dialog: AlertDialog = alert.create()
            dialog.show()
            true
        }
    }

    fun updateListView() {
        dataArray = prefenceManager.getSavedJournals()
        journalAdapter.clear()
        journalAdapter.addAll(dataArray)
        journalAdapter.notifyDataSetChanged()
    }

    fun showAddJournal() {
        val i = Intent(this, AddJournalActivity::class.java)
        startActivityForResult(i, 90)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }
}
