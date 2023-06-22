package dpsg.conduit

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import dpsg.conduit.api.models.Article
import org.w3c.dom.Text

class ArticleAdapter(private var articles: List<Article>) : RecyclerView.Adapter<ArticleAdapter.ArticleViewHolder>() {
    class ArticleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.title_text_view)
        val summary: TextView = itemView.findViewById(R.id.article_excerpt_text_view)
        val authorAvatar: ImageView = itemView.findViewById(R.id.article_author_pic)
        val author: TextView = itemView.findViewById(R.id.article_author_text_view)
    }

    fun setArticles(articles: List<Article>) {
        this.articles = articles;
        this.notifyItemRangeChanged(0, articles.size)
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
            .into(holder.authorAvatar)
        holder.author.text = article.author.username
    }
}