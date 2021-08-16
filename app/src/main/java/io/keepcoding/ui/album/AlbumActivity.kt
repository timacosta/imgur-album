package io.keepcoding.ui.album

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import io.keepcoding.R
import io.keepcoding.databinding.AlbumActivityBinding
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.album_activity)

        val binding = AlbumActivityBinding.inflate(layoutInflater).also { setContentView(it.root) }
        val adapter = AlbumRecyclerAdapter()
        binding.albumRecyclerView.adapter = adapter

        //viewModel.getAlbum()

    }


}