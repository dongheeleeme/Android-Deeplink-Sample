package me.dongheelee.deeplinksample.ui.detail

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import me.dongheelee.deeplinksample.R
import me.dongheelee.deeplinksample.core.CoreActivity
import me.dongheelee.deeplinksample.core.ext.loadUrl
import me.dongheelee.deeplinksample.data.PlayerRepository
import me.dongheelee.deeplinksample.data.model.Player

private const val EXTRA_ID = "extra_id"
private const val QUERY_ID = "id"

class DetailActivity : CoreActivity() {

    private lateinit var id: String

    override fun getLayoutResId(): Int = R.layout.activity_detail

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val player = findPlayerById()

        findViewById<TextView>(R.id.tv_name).text = player.name
        findViewById<TextView>(R.id.tv_number).text = player.formattedNumber()
        findViewById<TextView>(R.id.tv_country).text = player.cityCountry()
        findViewById<ImageView>(R.id.iv_thumbnail).loadUrl(player.thumbnail)
    }

    private fun findPlayerById(): Player {
        val players = PlayerRepository.getInstance(this).fetchPlayers()
        return players.find { it.id == id.toLong() } ?: error("")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putString(EXTRA_ID, id)
        super.onSaveInstanceState(outState)
    }

    override fun setSavedInstanceState(savedInstanceState: Bundle?) {
        val bundle = getBundle(savedInstanceState)
        bundle?.getString(EXTRA_ID)?.let { id = it } ?: run { finish() }
    }

    companion object {

        fun startActivity(context: Context, id: String) {
            context.startActivity(getIntent(context, id))
        }

        private fun getIntent(context: Context, id: String): Intent {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra(EXTRA_ID, id)
            return intent
        }

        fun getIntent(context: Context, deepLinkUri: Uri): Intent {
            val intent = Intent(context, DetailActivity::class.java)
            val id = deepLinkUri.getQueryParameter(QUERY_ID)
            intent.putExtra(EXTRA_ID, id)
            return intent
        }
    }
}
