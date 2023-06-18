package dpsg.conduit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.widget.Toolbar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONObject
import dpsg.conduit.api.apis.ArticlesApi
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

const val URL = "https://api.realworld.io/api"
fun getURL(path: String): String {
    return "$URL$path"
}

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        val recyclerView = findViewById<androidx.recyclerview.widget.RecyclerView>(R.id.article_list)
        recyclerView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this)

        CoroutineScope(Dispatchers.IO).launch {
            var articleApi = ArticlesApi()
            var articles = articleApi.getArticles()
            CoroutineScope(Dispatchers.Main).launch {
                recyclerView.adapter = ArticleAdapter(articles.articles)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_actions, menu)
        return super.onCreateOptionsMenu(menu)
    }
}