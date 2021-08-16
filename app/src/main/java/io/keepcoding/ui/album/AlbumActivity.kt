package io.keepcoding.ui.album

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import io.keepcoding.R
import io.keepcoding.databinding.AlbumActivityBinding
import kotlinx.coroutines.flow.collect
import org.kodein.di.DI
import org.kodein.di.DIAware
import org.kodein.di.android.di
import org.kodein.di.direct
import org.kodein.di.instance

class AlbumActivity : AppCompatActivity(), DIAware {

    override val di: DI by di()
    private val viewModel: AlbumViewModel by lazy {
        ViewModelProvider(this, direct.instance()).get(AlbumViewModel::class.java)
    }

    private lateinit var binding: AlbumActivityBinding
    private lateinit var adapter: AlbumRecyclerAdapter

    var albumID : String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.album_activity)

        binding = AlbumActivityBinding.inflate(layoutInflater).also { setContentView(it.root) }
        adapter = AlbumRecyclerAdapter().also {
            binding.albumRecyclerView.adapter = it
        }

        albumID = intent.getStringExtra("album_id")
        if(albumID != null) {
            viewModel.getAlbum(albumID!!)
        }

        lifecycleScope.launchWhenStarted {
            viewModel.albumState.collect {
                adapter.imageList = it.albumImages
            }
        }

    }


}