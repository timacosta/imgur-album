package io.keepcoding.ui.album

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import io.keepcoding.R
import io.keepcoding.databinding.AlbumActivityBinding
import io.keepcoding.databinding.MainActivityBinding
import io.keepcoding.ui.gallery.GalleryRecyclerAdapter
import kotlinx.coroutines.flow.collect
import org.kodein.di.DI
import org.kodein.di.DIAware
import org.kodein.di.android.di
import org.kodein.di.direct
import org.kodein.di.instance

class AlbumActivity : AppCompatActivity(), DIAware {

    override val di: DI by di()
    private lateinit var binding: AlbumActivityBinding
    private lateinit var adapter: AlbumRecyclerAdapter

    private val viewModel: AlbumViewModel by lazy {
        ViewModelProvider(this, direct.instance()).get(AlbumViewModel::class.java)
    }

    var albumID : String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = AlbumActivityBinding.inflate(layoutInflater).also {
            setContentView(it.root)
        }

        albumID = intent.getStringExtra("album_id")
        configureRecyclerView()
        getAlbumIfNotNull()
        viewModelConfig()

    }

    private fun configureRecyclerView() {
        binding = AlbumActivityBinding.inflate(layoutInflater).also { setContentView(it.root) }
        adapter = AlbumRecyclerAdapter().also { binding.albumRecyclerView.adapter = it }
    }

    private fun getAlbumIfNotNull() {
        if(albumID != null){
            viewModel.getAlbum(albumID!!)
        }
    }

    private fun viewModelConfig() {
        lifecycleScope.launchWhenStarted {
            viewModel.albumState.collect {
                adapter.imageList = it.albumImages
            }
        }
    }
}