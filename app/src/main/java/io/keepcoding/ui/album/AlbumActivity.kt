package io.keepcoding.ui.album

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import io.keepcoding.R
import io.keepcoding.databinding.AlbumActivityBinding
import org.kodein.di.DI
import org.kodein.di.DIAware
import org.kodein.di.android.di

class AlbumActivity : AppCompatActivity(), DIAware {

    override val di: DI by di()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.album_activity)

        val binding = AlbumActivityBinding.inflate(layoutInflater).also { setContentView(it.root) }
        val adapter = AlbumRecyclerAdapter()
        binding.albumRecyclerView.adapter = adapter

    }


}