package layouts.pme.dk.searchbar

import com.google.gson.Gson
import okhttp3.*
import java.io.IOException

class RepoMetworkManager {

    val okHttpClient: OkHttpClient = OkHttpClient()

    fun getData(query: String, onCompletion: (Boolean, ArrayList<GithubSearch>?) -> Unit) {
        val urlWithKotlin = "https://api.github.com/search/repositories?q="+query+"+language%3Akotlin&sort=stars"

        val request: Request = Request.Builder().url(urlWithKotlin).build()
        okHttpClient.newCall(request).enqueue(object: Callback {
            override fun onFailure(call: Call?, e: IOException?) {
                onCompletion(false, null)
            }
            override fun onResponse(call: Call?, response: Response?) {
                val json = response?.body()?.string()

                val gson = Gson()
                val githubResponse = gson.fromJson(json, GithubSearchResponse::class.java)

                onCompletion(true, githubResponse.items)
            }
        })
    }
}