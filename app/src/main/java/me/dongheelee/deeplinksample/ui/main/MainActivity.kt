package me.dongheelee.deeplinksample.ui.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import me.dongheelee.deeplinksample.R
import me.dongheelee.deeplinksample.core.CoreActivity
import me.dongheelee.deeplinksample.data.PlayerRepository
import me.dongheelee.deeplinksample.ui.detail.DetailActivity

class MainActivity : CoreActivity() {

    override fun getLayoutResId(): Int = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        val adapter = PlayerAdapter {
            DetailActivity.startActivity(this, it.toString())
        }
        findViewById<RecyclerView>(R.id.rv_product).adapter = adapter
        adapter.setItems(PlayerRepository.getInstance(this).fetchPlayers())
    }

    companion object {
        fun getIntent(context: Context): Intent {
            val intent = Intent(context, MainActivity::class.java)
            intent.addFlags(
                Intent.FLAG_ACTIVITY_CLEAR_TOP
                        or Intent.FLAG_ACTIVITY_SINGLE_TOP
                        or Intent.FLAG_ACTIVITY_CLEAR_TASK
            )
            return intent
        }
    }
}
