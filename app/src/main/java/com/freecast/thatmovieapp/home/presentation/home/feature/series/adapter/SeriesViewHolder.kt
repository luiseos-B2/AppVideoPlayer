package com.freecast.thatmovieapp.home.presentation.home.feature.series.adapter

import androidx.recyclerview.widget.RecyclerView
import com.freecast.thatmovieapp.core.presentation.util.loadImage
import com.freecast.thatmovieapp.core.presentation.util.toImageUrl
import com.freecast.thatmovieapp.databinding.MediaItemBinding
import com.freecast.thatmovieapp.home.domain.model.Media
import com.freecast.thatmovieapp.home.presentation.util.listener.MediaDetailListener
import com.freecast.thatmovieapp.home.domain.model.MediaType

class SeriesViewHolder(
    private val binding: MediaItemBinding,
    private val listener: MediaDetailListener?
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(media: Media) {
        binding.moviePoster.loadImage(media.posterPath.toImageUrl())
        binding.root.setOnClickListener {
            listener?.onDetailsMediaClicked(MediaType.SERIES, media)
        }
    }
}