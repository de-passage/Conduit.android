package dpsg.conduit

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import dpsg.conduit.api.models.Article
import org.w3c.dom.Text
import java.time.DateTimeException
import java.time.format.DateTimeFormatter

class ArticleAdapter(private var articles: List<Article>) : RecyclerView.Adapter<ArticleAdapter.ArticleViewHolder>() {
    class ArticleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.title_text_view)
        val summary: TextView = itemView.findViewById(R.id.article_excerpt_text_view)
        val authorAvatar: ImageView = itemView.findViewById(R.id.article_author_pic)
        val author: TextView = itemView.findViewById(R.id.article_author_text_view)
        val date: TextView = itemView.findViewById(R.id.article_date_text_view)
        var slug: String? = null
        init {
            itemView.setOnClickListener { view ->
                if (slug == null) {
                    Toast.makeText(view.context, "This article doesn't have content", Toast.LENGTH_SHORT).show()
                } else {
                    val context = view.context
                    val intent = Intent(context, ShowArticle::class.java)
                    intent.putExtra(ShowArticle.EXTRA_ARTICLE_TITLE, title.text)
                    intent.putExtra(ShowArticle.EXTRA_ARTICLE_DESCRIPTION, summary.text)
                    intent.putExtra(ShowArticle.EXTRA_ARTICLE_SLUG, slug)
                    context.startActivity(intent)
                }
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setArticles(articles: List<Article>) {
        this.articles = articles;
        this.notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.article_list_layout, parent, false)
        return ArticleViewHolder(view)
    }

    override fun getItemCount(): Int {
        return articles.size
    }


    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val article = articles[position]
        holder.title.text = article.title
        holder.summary.text = article.description
        Glide.with(holder.itemView.context)
            .load(article.author.image)
            .circleCrop()
            .into(holder.authorAvatar)
        holder.author.text = article.author.username
        holder.slug = article.slug
        holder.date.text = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
            val date = article.createdAt
            try {
                val parsedDate = formatter.parse(date.toString())
                val formatter2 = DateTimeFormatter.ofPattern("dd.MM.yyyy")
                formatter2.format(parsedDate).toString()
            } catch (e: DateTimeException) {
                date.toString()
            }
        } else {
            article.createdAt.toString()
        }
    }
}