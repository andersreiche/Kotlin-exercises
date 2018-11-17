package layouts.pme.dk.searchbar

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName

data class GithubSearchResponse(val items: ArrayList<GithubSearch>)

data class GithubSearch(@SerializedName("name") val title: String,
                        @SerializedName("owner") val author: GithubRepoOwner,
                        @SerializedName("created_at") var created: String,
                        @SerializedName("stargazers_count") val stars: String,
                        @SerializedName("html_url") val url:String) {
    companion object {
        fun deSerialize(json: String): GithubSearch {
            val gson = Gson()
            val item = gson.fromJson(json, GithubSearch::class.java)

            return item
        }
    }
    fun serialize(): String {
        val gson = Gson()
        val jsonString = gson.toJson(this)

        return jsonString
    }
}
data class GithubRepoOwner(@SerializedName("login") val name: String,
                           @SerializedName("avatar_url") val avatarUrl: String)
