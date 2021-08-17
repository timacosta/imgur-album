package io.keepcoding.ui.gallery

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import io.keepcoding.gallery.Image
import io.keepcoding.databinding.GalleryItemBinding
import io.keepcoding.ui.album.AlbumActivity

class GalleryRecyclerAdapter : RecyclerView.Adapter<GalleryViewHolder>() {

    var imageList: List<Image> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GalleryViewHolder =
        GalleryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            .run { GalleryViewHolder(this) }

    override fun onBindViewHolder(holder: GalleryViewHolder, position: Int) {
        holder.bind(imageList[position])
    }

    override fun getItemCount(): Int = imageList.size
}

data class GalleryViewHolder(val binding: GalleryItemBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(image: Image) {
        with(binding) {
            titleTextView.text = image.title ?: "No title"
            authorTextView.text = image.author ?: "Unknown"
            imageView.setImageBitmap(null)
            authorAvatarImageView.setImageBitmap(null)
            Glide.with(root).load(image.url).into(imageView)
            Glide.with(root).load(image.authorAvatar).also {
                it.circleCrop()
            }.into(authorAvatarImageView)

            expandAlbumButton.visibility = when (image.isAlbum && image.albumImagesCount > 1) {
                true -> View.VISIBLE
                false -> View.GONE
            }

            expandAlbumButton.setOnClickListener {
                val intent = Intent(itemView.context, AlbumActivity::class.java)
                intent.putExtra("album_id", image.id)
                itemView.context.startActivity(intent)
            }
        }
    }
}