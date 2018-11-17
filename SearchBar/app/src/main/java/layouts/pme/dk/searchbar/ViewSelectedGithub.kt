package layouts.pme.dk.searchbar

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_view_selected_github.*
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.google.gson.Gson
import kotlinx.android.synthetic.*
import layouts.pme.dk.searchbar.GithubSearch.Companion.deSerialize
import java.net.URL



class ViewSelectedGithub : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_selected_github)
        setSupportActionBar(toolbar)

        toolbar.setNavigationOnClickListener { _ ->
            finish()
        }

        var json= intent.extras.get("json").toString()
        var gson = deSerialize(json)

        toolbar.setTitle(gson.title);
        Author_override.text = gson.author.name
        Stars_override.text = gson.stars

        val day = gson.created.subSequence(8,10).toString()
        val month = gson.created.subSequence(5,7).toString()
        val year = gson.created.subSequence(0,4).toString()
        val mydate = day+"/"+month+"-"+year
        Created_override.text = mydate

        Glide.with(this)
                .load(gson.author.avatarUrl)
                .into(avatar)

        OpenGithub.setOnClickListener(){
            val i = Intent(Intent.ACTION_VIEW)
            i.setData(Uri.parse(gson.url))
            startActivity(i)
        }
    }


}
