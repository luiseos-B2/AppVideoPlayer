package com.freecast.thatmovieapp.home.presentation.home.feature.movie.fragment

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import com.freecast.thatmovieapp.R
import com.freecast.thatmovieapp.core.domain.util.Resource
import com.freecast.thatmovieapp.databinding.FragmentHomeBinding
import com.freecast.thatmovieapp.core.presentation.base.BaseFragment
import com.freecast.thatmovieapp.home.domain.model.Media
import com.freecast.thatmovieapp.home.presentation.home.feature.movie.adapter.MovieAdapter
import com.freecast.thatmovieapp.home.presentation.home.feature.movie.viewmodel.MovieViewModel
import com.freecast.thatmovieapp.home.presentation.util.listener.MediaDetailListener
import com.freecast.thatmovieapp.home.domain.model.MediaType
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.util.MimeTypes
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class MovieFragment : BaseFragment(), MediaDetailListener, Player.Listener {

    private lateinit var binding: FragmentHomeBinding
    private val viewModel: MovieViewModel by viewModel()
    private val adapter: MovieAdapter by inject()

    private var player: Player? = null
    private var playWhenReady = false
    private var mediaItemIndex = 0
    private var playbackPosition = 0L

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getTopRatedMovies()
        setupRecycler()
        setupObservers()
    }

    private fun setupRecycler() {
        binding.rvMovies.adapter = adapter
        adapter.listener = this
    }

    private fun setupObservers() {
        viewModel.topRatedMoviesLiveData.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Loading -> {
                    updateContent(showProgress = true)
                }
                is Resource.Success -> {
                    it.data?.let { pagingData ->
                        adapter.submitData(lifecycle, pagingData)
                    }
                    updateContent(showProgress = false)
                }
                is Resource.Error -> {
                    updateContent(false)
                    Toast.makeText(requireContext(), getString(R.string.error_message), Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onDetailsMediaClicked(mediaType: MediaType, media: Media) {
        openDetailsMovie(mediaType, media)
    }

    private fun openDetailsMovie(mediaType: MediaType, media: Media) {
        val bundle = bundleOf(MEDIA_KEY to media, MEDIA_TYPE_KEY to mediaType)
        findNavController().navigate(R.id.action_homeFragment_to_detailsMovieActivity, bundle)
    }

    override fun updateContent(showProgress: Boolean) {
        binding.homeProgressBar.isVisible = showProgress
        binding.content.isVisible = !showProgress
    }

    private fun initializePlayer() {
        player = ExoPlayer.Builder(requireContext())
            .build()
            .apply {
                binding.playerView.player = this
                trackSelectionParameters = this.trackSelectionParameters
                    .buildUpon()
                    .setMaxVideoSizeSd()
                    .build()

                val mediaItem = MediaItem.Builder()
                    .setUri(getString(R.string.media_url_dash))
                    .setMimeType(MimeTypes.APPLICATION_MPD)
                    .build()
                setMediaItems(listOf(mediaItem), mediaItemIndex, playbackPosition)
                playWhenReady = playWhenReady
                addListener(this@MovieFragment)
                prepare()
            }
    }

    override fun onStart() {
        super.onStart()
        if (Build.VERSION.SDK_INT >= 24) {
            initializePlayer()
        }
    }

    override fun onResume() {
        super.onResume()
        hideSystemUi()
        if (Build.VERSION.SDK_INT < 24 || player == null) {
            initializePlayer()
        }
    }

    override fun onPause() {
        super.onPause()
        if (Build.VERSION.SDK_INT < 24) {
            releasePlayer()
        }
    }

    override fun onStop() {
        super.onStop()
        if (Build.VERSION.SDK_INT >= 24) {
            releasePlayer()
        }
    }

    private fun releasePlayer() {
        player?.let { player ->
            playbackPosition = player.currentPosition
            mediaItemIndex = player.currentMediaItemIndex
            playWhenReady = player.playWhenReady
            player.removeListener(this)
            player.release()
        }
        player = null
    }

    @SuppressLint("InlinedApi")
    private fun hideSystemUi() {
        WindowCompat.setDecorFitsSystemWindows(requireActivity().window, false)
        WindowInsetsControllerCompat(requireActivity().window, binding.playerView).let { controller ->
            controller.hide(WindowInsetsCompat.Type.systemBars())
            controller.systemBarsBehavior =
                WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        }
    }

    override fun onPlaybackStateChanged(playbackState: Int) {
        super.onPlaybackStateChanged(playbackState)
        when (playbackState) {
            ExoPlayer.STATE_IDLE -> {
                Log.d(TAG, "onPlaybackStateChanged: STATE_IDLE")
            }
            ExoPlayer.STATE_BUFFERING -> {
                Log.d(TAG, "onPlaybackStateChanged: STATE_BUFFERING")
            }
            ExoPlayer.STATE_READY -> {
                Log.d(TAG, "onPlaybackStateChanged: STATE_READY")
            }
            ExoPlayer.STATE_ENDED -> {
                Log.d(TAG, "onPlaybackStateChanged: STATE_ENDED")
            }
        }
    }

    companion object {
        const val MEDIA_KEY = "MEDIA"
        const val MEDIA_TYPE_KEY = "MEDIA_TYPE"
    }
}