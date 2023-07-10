package com.freecast.thatmovieapp.home.presentation.home.feature.series.adapter

import MediaComparator
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import com.freecast.thatmovieapp.databinding.MediaItemBinding
import com.freecast.thatmovieapp.home.domain.model.Media
import com.freecast.thatmovieapp.home.presentation.util.listener.MediaDetailListener

class SeriesAdapter(
    mediaComparator: MediaComparator
): PagingDataAdapter<Media, SeriesViewHolder>(mediaComparator) {

        var listener: MediaDetailListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SeriesViewHolder {
        return SeriesViewHolder(MediaItemBinding.inflate(LayoutInflater.from(parent.context), parent, false), listener)
    }

    override fun onBindViewHolder(holder: SeriesViewHolder, position: Int) {
        getItem(position)?.let {media ->
            holder.bind(media)
        }
    }
}