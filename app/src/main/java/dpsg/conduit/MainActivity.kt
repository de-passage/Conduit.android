package dpsg.conduit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.widget.Toolbar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONObject
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

const val URL = "https://api.realworld.io/api";
fun getURL(path: String): String {
    return "$URL$path"
}

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar = findViewById<Toolbar>(R.id.toolbar);
        setSupportActionBar(toolbar);
        getArticles()

        val recyclerView = findViewById<androidx.recyclerview.widget.RecyclerView>(R.id.article_list);
        recyclerView.adapter = ArticleAdapter(listOf());
        recyclerView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this);
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_actions, menu);
        return super.onCreateOptionsMenu(menu);
    }
    // Function to fetch data from a given url
    private fun fetchUrl(target: String): JSONObject {
        lateinit var connection: HttpURLConnection
        lateinit var reader: BufferedReader

        try {
            val url = URL(target)
            connection = url.openConnection() as HttpURLConnection
            connection.connect()

            val stream = connection.inputStream
            reader = BufferedReader(InputStreamReader(stream))
            val buffer = StringBuffer()
            var line: String? = ""

            while (reader.readLine().also { line = it } != null) {
                buffer.append(line + "\n")
            }

            return JSONObject(buffer.toString());

        } catch (e: Exception) {
            e.printStackTrace()
            throw e;
        } finally {
            connection?.disconnect()
            try {
                reader?.close()
            } catch (e: IOException) {
                e.printStackTrace()
                throw e;
            }
        }
    }

    private fun getArticles() {
        val url = getURL("/articles")
        CoroutineScope(Dispatchers.IO).launch {
            val response = fetchUrl(url)
            println(response)
            var articles = response.getJSONArray("articles");
            var list = mutableListOf<JSONObject>();
            for (i in 0 until articles.length()) {
                val article = articles.getJSONObject(i);
                list.add(article);
            }
            runOnUiThread {
                val recyclerView = findViewById<androidx.recyclerview.widget.RecyclerView>(R.id.article_list);
                val adapter = recyclerView.adapter as ArticleAdapter;
                adapter.setArticles(list);
            }
        }
    }
}