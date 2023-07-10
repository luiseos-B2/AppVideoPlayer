package com.freecast.thatmovieapp.home.presentation.mediadetails.activity

import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.core.view.isVisible
import com.freecast.thatmovieapp.R
import com.freecast.thatmovieapp.core.domain.util.Resource
import com.freecast.thatmovieapp.core.presentation.base.BaseActivity
import com.freecast.thatmovieapp.databinding.ActivityDetailsMovieBinding
import com.freecast.thatmovieapp.core.presentation.util.loadImage
import com.freecast.thatmovieapp.core.presentation.util.toImageUrl
import com.freecast.thatmovieapp.home.domain.model.Media
import com.freecast.thatmovieapp.home.domain.model.MediaDetails
import com.freecast.thatmovieapp.home.presentation.mediadetails.viewmodel.MediaDetailViewModel
import com.freecast.thatmovieapp.home.domain.model.MediaType
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailsMovieActivity : BaseActivity() {

    private lateinit var binding: ActivityDetailsMovieBinding
    private val viewModel: MediaDetailViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getSerializableExtra(MEDIA_KEY, Media::class.java)?.let {
                intent.getSerializableExtra(MEDIA_TYPE_KEY, MediaType::class.java)?.let { mediaType ->
                    getMovieDetail(mediaType, it.id)
                }
            }
        } else {
            intent.getSerializableExtra(MEDIA_KEY)?.let {
                intent.getSerializableExtra(MEDIA_TYPE_KEY)?.let { mediaType ->
                    getMovieDetail(mediaType as MediaType, (it as Media).id)
                }
            }
        }
        setupObservers()

        binding.mtMediaDetails.setNavigationOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }

    private fun getMovieDetail(mediaType: MediaType, id: Int) {
        when (mediaType) {
            MediaType.MOVIE -> {
                viewModel.getMovieDetail(id)
            }
            MediaType.SERIES -> {
                viewModel.getSeriesDetail(id)
            }
        }
    }

    private fun setupObservers() {
        viewModel.detailMediaLiveData.observe(this) {
            when (it) {
                is Resource.Loading -> {
                    updateContent(true)
                }
                is Resource.Success -> {
                    updateContent(false)
                    populateDetails(it.data)
                }
                is Resource.Error -> {
                    updateContent(false)
                    binding.contentError.isVisible = true
                    Toast.makeText(this, getString(R.string.error_message), Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun updateContent(showProgress: Boolean) {
        binding.detailMediaProgressBar.isVisible = showProgress
        binding.content.isVisible = !showProgress
    }

    private fun populateDetails(mediaDetail: MediaDetails?) {
        binding.mediaBackdrop.loadImage(mediaDetail?.backdropPath.toImageUrl())
        binding.mediaPoster.loadImage(mediaDetail?.posterPath.toImageUrl())
        binding.mtMediaDetails.title = mediaDetail?.title ?: getText(R.string.name_not_available)
        binding.mediaTitle.text = mediaDetail?.title ?: getText(R.string.name_not_available)
        binding.mediaRating.rating = mediaDetail?.rating?.div(2) ?: 0f
        binding.mediaReleaseDate.text = mediaDetail?.releaseDate ?: getText(R.string.date_not_available)
        binding.mediaOverview.text = mediaDetail?.overview ?: getText(R.string.no_description_available)
        binding.mediaGenres.text = mediaDetail?.genres?.joinToString { it.name.toString()} ?: getText(R.string.genre_not_available)
        binding.mediaRuntime.text = mediaDetail?.runtime?.let { getString(R.string.runtime_value, it.toString()) } ?: getText(R.string.runtime_not_available)
    }

    companion object {
        const val MEDIA_KEY = "MEDIA"
        const val MEDIA_TYPE_KEY = "MEDIA_TYPE"
    }
}