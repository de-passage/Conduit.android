package dpsg.conduit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import dpsg.conduit.api.apis.ArticlesApi

const val URL = "https://api.realworld.io/api"
fun getURL(path: String): String {
    return "$URL$path"
}

class MainActivity : AppCompatActivity() {

    private val recyclerView: androidx.recyclerview.widget.RecyclerView by lazy {
        findViewById(R.id.article_list)
    }
    private val progressBar: android.widget.ProgressBar by lazy {
        findViewById(R.id.progress_circular)
    }
    private var adapter: ArticleAdapter = ArticleAdapter(emptyList())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        recyclerView.adapter = adapter

        recyclerView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this)

        loadArticles()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_actions, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here
        when (item.itemId) {
            R.id.reload -> {
                // Respond to the action bar's Up/Home button
                loadArticles()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun loadArticles() {
        progressBar.visibility = android.view.View.VISIBLE
        adapter.setArticles(emptyList())

        CoroutineScope(Dispatchers.IO).launch {
            var articleApi = ArticlesApi()
            var articles = articleApi.getArticles()
            CoroutineScope(Dispatchers.Main).launch {
                progressBar.visibility = android.view.View.GONE
                adapter.setArticles(articles.articles)
            }
        }
    }
}