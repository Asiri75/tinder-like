package com.libertytech.tinderlike.screens.favorite

import android.os.Bundle
import androidx.lifecycle.ViewModel
import com.libertytech.tinderlike.R
import com.libertytech.tinderlike.model.Like

class FavoriteViewModel : ViewModel() {
    private lateinit var likeDao: LikeDao
    private lateinit var favoritesAdapter: FavoritesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorites)

        // Initialiser le RecyclerView
        val favoritesRecyclerView: RecyclerView = findViewById(R.id.favoritesRecyclerView)
        favoritesRecyclerView.layoutManager = LinearLayoutManager(this)
        favoritesAdapter = FavoritesAdapter()
        favoritesRecyclerView.adapter = favoritesAdapter

        // Récupérer les "j'aime" de la base de données
        likeDao = YourDatabase.getInstance(this).getLikeDao()
        val likes: List<Like> = likeDao.getAllLikes()

        // Mettre à jour l'adaptateur avec les données des "j'aime"
        favoritesAdapter.setLikes(likes)
    }
}