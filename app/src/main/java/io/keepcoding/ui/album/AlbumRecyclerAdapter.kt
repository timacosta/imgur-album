package io.keepcoding.ui.album

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import io.keepcoding.databinding.AlbumItemBinding
import io.keepcoding.gallery.Image

class AlbumRecyclerAdapter : RecyclerView.Adapter<AlbumViewHolder>() {

    var imageList: List<Image> = emptyList()
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
    fun bind(image: Image) {
        with(binding) {
            imageView.setImageBitmap(null)
            Glide.with(root).load(image.url).into(imageView)
        }
    }
}