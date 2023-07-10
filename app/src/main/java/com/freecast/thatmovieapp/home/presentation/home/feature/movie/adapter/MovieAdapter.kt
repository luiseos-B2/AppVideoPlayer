package com.freecast.thatmovieapp.home.presentation.home.feature.movie.adapter

import MediaComparator
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import com.freecast.thatmovieapp.databinding.MediaItemBinding
import com.freecast.thatmovieapp.home.domain.model.Media
import com.freecast.thatmovieapp.home.presentation.util.listener.MediaDetailListener

class MovieAdapter(
    mediaComparator: MediaComparator
) : PagingDataAdapter<Media, MovieViewHolder>(mediaComparator) {

    var listener: MediaDetailListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(
            MediaItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ), listener
        )
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }
    }
}