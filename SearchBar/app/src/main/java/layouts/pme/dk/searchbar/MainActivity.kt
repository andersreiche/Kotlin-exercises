package layouts.pme.dk.searchbar

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.SearchView
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import kotlinx.android.synthetic.main.activity_main.*
import java.text.DateFormat


class MainActivity : AppCompatActivity() {

    val networkManager = RepoMetworkManager()
    lateinit var myAdapter: GithubAdapter
    val githubList = ArrayList<GithubSearch>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        myAdapter = GithubAdapter(this, R.layout.list_item, githubList)
        listView.adapter = myAdapter

        listView.setOnItemClickListener{ _, _, position, _ ->

            val clickedItem = githubList.get(position)

            val i = Intent(this, ViewSelectedGithub::class.java)
            i.putExtra("json", clickedItem.serialize())
            startActivity(i)
        }
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.search_menu, menu)

        val searchItem = menu?.findItem(R.id.searchBar)

        if (searchItem != null) {
            val searchView = searchItem.actionView as SearchView
            searchView.queryHint = "Github-search"

            searchView.isIconified = false
            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    if (query != null) {
                        networkManager.getData(query) { didSucceed, items ->
                            runOnUiThread {
                                if (didSucceed && items != null) {
                                    githubList.clear()
                                    githubList.addAll(items)
                                    myAdapter.notifyDataSetChanged()
                                } else {
                                    Toast.makeText(baseContext, "Error on search", Toast.LENGTH_SHORT).show()
                                }
                            }
                        }
                    }
                    //Return false to hide keyboard, true to keep it shown
                    return false
                }
                override fun onQueryTextChange(newText: String?): Boolean {
                    //This method is called upon when text is changed
                    return false
                }
            })
        }
        return super.onCreateOptionsMenu(menu)
    }
}
