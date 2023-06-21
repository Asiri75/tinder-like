package com.libertytech.tinderlike.screens.favorite

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import com.libertytech.tinderlike.R
import com.libertytech.tinderlike.model.Like

class FavoritesAdapter : RecyclerView.Adapter<FavoritesAdapter.ViewHolder>() {
    private var likes: List<Like> = emptyList()

    fun setLikes(likes: List<Like>) {
        this.likes = likes
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_favorite, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val like = likes[position]

        // Afficher les données des "j'aime" dans l'élément de liste
        holder.bind(like)
    }

    override fun getItemCount(): Int {
        return likes.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(like: Like) {
            // Mettre à jour les vues de l'élément de liste avec les données des "j'aime"
            itemView.findViewById<TextView>(R.id.userNameTextView).text = like.userId
            itemView.findViewById<TextView>(R.id.likeDateTextView).text = like.likeDate
        }
    }
}