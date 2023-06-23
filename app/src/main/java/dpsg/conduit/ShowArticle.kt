package dpsg.conduit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.core.app.NavUtils
import com.bumptech.glide.Glide
import dpsg.conduit.api.apis.ArticlesApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ShowArticle : AppCompatActivity() {
    companion object {

        const val EXTRA_ARTICLE_SLUG = "article_slug"
        const val EXTRA_ARTICLE_TITLE = "article_title"
        const val EXTRA_ARTICLE_DESCRIPTION = "article_description"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        val apiAdapter = ArticlesApi()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_article)
        val articleSlug = intent.getStringExtra(EXTRA_ARTICLE_SLUG)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        if (articleSlug == null) {
            Toast.makeText(this.applicationContext, "No slug for this article", Toast.LENGTH_SHORT).show()
            finish()
            return
        }
        val articleTitle = intent.getStringExtra(EXTRA_ARTICLE_TITLE)
        val articleDescription = intent.getStringExtra(EXTRA_ARTICLE_DESCRIPTION)
        val articleTitleTextView: TextView = findViewById(R.id.article_title)
        articleTitleTextView.text = articleTitle
        val articleDescriptionTextView: TextView = findViewById(R.id.article_content)
        articleDescriptionTextView.text = articleDescription

        CoroutineScope(Dispatchers.IO).launch {
            val response = apiAdapter.getArticle(articleSlug);
            CoroutineScope(Dispatchers.Main).launch {
                articleTitleTextView.text = response.article.title
                articleDescriptionTextView.text = response.article.body
                Glide.with(this@ShowArticle)
                    .load(response.article.author.image)
                    .circleCrop()
                    .into(findViewById(R.id.article_author_pic));
                findViewById<TextView>(R.id.article_author).text = response.article.author.username
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here
        when (item.itemId) {
            android.R.id.home -> {
                // Respond to the action bar's Up/Home button
                NavUtils.navigateUpFromSameTask(this)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}