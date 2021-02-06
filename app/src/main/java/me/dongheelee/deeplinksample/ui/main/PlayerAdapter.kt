package me.dongheelee.deeplinksample.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import me.dongheelee.deeplinksample.R
import me.dongheelee.deeplinksample.core.ext.loadUrl
import me.dongheelee.deeplinksample.data.model.Player

typealias OnItemClickListener = (Long) -> Unit

class PlayerAdapter(
    private val onItemClickListener: OnItemClickListener
) : RecyclerView.Adapter<PlayerAdapter.ViewHolder>() {

    private val items = mutableListOf<Player>()

    fun setItems(items: List<Player>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.item_player,
            parent,
            false
        )
        return ViewHolder(itemView, onItemClickListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = items.size

    class ViewHolder(
        itemView: View,
        private val onItemClickListener: OnItemClickListener
    ) : RecyclerView.ViewHolder(itemView) {

        private val name = itemView.findViewById<TextView>(R.id.tv_name)
        private val country = itemView.findViewById<TextView>(R.id.tv_country)
        private val thumbnail = itemView.findViewById<ImageView>(R.id.iv_thumbnail)
        private val number = itemView.findViewById<TextView>(R.id.tv_number)

        fun bind(player: Player) {
            itemView.setOnClickListener {
                onItemClickListener.invoke(player.id)
            }
            thumbnail.loadUrl(player.thumbnail)
            name.text = player.name
            country.text = player.country
            number.text = player.number.toString()
        }
    }
}
