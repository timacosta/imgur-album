package io.keepcoding.ui.album

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.keepcoding.databinding.AlbumItemBinding

class AlbumRecyclerAdapter : RecyclerView.Adapter<AlbumViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

}


data class AlbumViewHolder(val binding: AlbumItemBinding): RecyclerView.ViewHolder(binding.root) {

}