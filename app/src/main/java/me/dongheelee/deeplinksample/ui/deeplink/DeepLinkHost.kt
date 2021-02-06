package me.dongheelee.deeplinksample.ui.deeplink

import android.content.Context
import android.content.Intent
import android.net.Uri
import me.dongheelee.deeplinksample.ui.detail.DetailActivity
import me.dongheelee.deeplinksample.ui.main.MainActivity

enum class DeepLinkHost {
    MAIN {
        override fun getIntent(context: Context, deepLinkUri: Uri): Intent =
            getMainIntent(context)

        override fun getHost(): String = "main"
    },
    DETAIL {
        override fun getIntent(context: Context, deepLinkUri: Uri): Intent =
            DetailActivity.getIntent(context, deepLinkUri)

        override fun getHost(): String = "detail"
    },
    ;

    abstract fun getIntent(context: Context, deepLinkUri: Uri): Intent
    abstract fun getHost(): String

    companion object {
        fun getMainIntent(context: Context) = MainActivity.getIntent(context)
        fun invoke(uri: Uri): DeepLinkHost = values().find { it.getHost() == uri.host } ?: MAIN
    }
}
