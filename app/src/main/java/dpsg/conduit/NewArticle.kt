package dpsg.conduit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.MenuItemCompat

class NewArticle : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_article)
        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}