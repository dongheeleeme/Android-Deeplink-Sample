package me.dongheelee.deeplinksample.data

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import me.dongheelee.deeplinksample.data.model.Player
import me.dongheelee.deeplinksample.R

class PlayerRepository(private val context: Context) {

    fun fetchPlayers(): List<Player> {
       val playersType = object : TypeToken<List<Player>>() {}.type
        return Gson().fromJson(getPlayerJsonStr(), playersType)
    }

    private fun getPlayerJsonStr(): String =
        context.resources.openRawResource(R.raw.players).bufferedReader().use { it.readText() }

    companion object {
        private var instance: PlayerRepository? = null

        fun getInstance(context: Context): PlayerRepository {
            if (instance == null) {
                instance = PlayerRepository(context)
            }
            return requireNotNull(instance)
        }
    }
}
