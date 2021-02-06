package me.dongheelee.deeplinksample.ui.deeplink

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.TaskStackBuilder
import me.dongheelee.deeplinksample.ui.main.MainActivity

class SchemeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        handleDeepLink()
    }

    private fun handleDeepLink() {
        val deepLinkUri = intent.data
        val deepLinkIntent = getDeepLinkIntent(deepLinkUri)

        if (isTaskRoot) {
            val taskStackBuilder = TaskStackBuilder.create(this)
            if (needAddMainForParent(deepLinkIntent)) {
                taskStackBuilder.addNextIntentWithParentStack(DeepLinkHost.getMainIntent(this))
            }
            taskStackBuilder.addNextIntent(deepLinkIntent)
            taskStackBuilder.startActivities()
        } else {
            startActivity(deepLinkIntent)
        }
        finish()
    }

    private fun getDeepLinkIntent(deepLinkUri: Uri?) =
        if (deepLinkUri != null) {
            DeepLinkHost.invoke(deepLinkUri).getIntent(this, deepLinkUri)
        } else {
            DeepLinkHost.getMainIntent(this)
        }

    private fun needAddMainForParent(intent: Intent) =
        when (intent.component?.className) {
            MainActivity::class.java.name -> false
            else -> true
        }
}
