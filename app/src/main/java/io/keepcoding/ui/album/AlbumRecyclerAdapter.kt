package io.keepcoding.ui.album

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import io.keepcoding.databinding.AlbumItemBinding
import io.keepcoding.gallery.AlbumImage
import io.keepcoding.gallery.Image
import io.keepcoding.load

class AlbumRecyclerAdapter : RecyclerView.Adapter<AlbumViewHolder>() {

    var imageList: List<AlbumImage> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder =
        AlbumItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            .run { AlbumViewHolder(this) }


    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        holder.bind(imageList[position])
    }

    override fun getItemCount(): Int = imageList.size

}

data class AlbumViewHolder(val binding: AlbumItemBinding): RecyclerView.ViewHolder(binding.root) {
    fun bind(image: AlbumImage) {
        with(binding) {
            imageView.setImageBitmap(null)
            imageView.load(image.link)
        }
    }
}