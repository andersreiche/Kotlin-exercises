package layouts.pme.dk.searchbar

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import java.text.SimpleDateFormat
import java.util.*

class GithubAdapter(context: Context?, resource: Int, objects: MutableList<GithubSearch>?)
    : ArrayAdapter<GithubSearch>(context, resource, objects){

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View{

        var listItemView = convertView

        if(listItemView == null){
            listItemView = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false)
        }
        val gitItem = getItem(position)

        val textViewTitle = listItemView?.findViewById<TextView>(R.id.Title)
        textViewTitle?.text     = gitItem.title

        val textViewAuthor= listItemView?.findViewById<TextView>(R.id.Author)
        textViewAuthor?.text    = gitItem.author.name

        val textViewCreated = listItemView?.findViewById<TextView>(R.id.Created)

        val day = gitItem.created.subSequence(8,10).toString()
        val month = gitItem.created.subSequence(5,7).toString()
        val year = gitItem.created.subSequence(0,4).toString()

        val mydate = day+"/"+month+"-"+year

        textViewCreated?.text   = mydate

        val textViewStar = listItemView?.findViewById<TextView>(R.id.Stars)
        textViewStar?.text = gitItem.stars

        return listItemView as View
    }
}