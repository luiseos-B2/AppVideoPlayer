package com.freecast.thatmovieapp.home.presentation.home.feature.series.fragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import com.freecast.thatmovieapp.R
import com.freecast.thatmovieapp.core.domain.util.Resource
import com.freecast.thatmovieapp.core.presentation.base.BaseFragment
import com.freecast.thatmovieapp.databinding.FragmentGenreBinding
import com.freecast.thatmovieapp.home.domain.model.Genre
import com.freecast.thatmovieapp.home.domain.model.Media
import com.freecast.thatmovieapp.home.presentation.home.feature.series.adapter.SeriesAdapter
import com.freecast.thatmovieapp.home.presentation.home.feature.series.viewmodel.SeriesViewModel
import com.freecast.thatmovieapp.home.presentation.util.listener.MediaDetailListener
import com.freecast.thatmovieapp.home.domain.model.MediaType
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class SeriesFragment : BaseFragment(), MediaDetailListener {

    private lateinit var binding: FragmentGenreBinding
    private val viewModel: SeriesViewModel by viewModel()
    private val adapter: SeriesAdapter by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGenreBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getGenres()
        setupRecyclers()
        setupObservers()
        configClickItemDropdown()
    }

    private fun setupRecyclers() {
        binding.rvGenre.adapter = adapter
        adapter.listener = this
    }

    private fun setupObservers() {
        viewModel.genresLiveData.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Loading -> {
                    updateContent(showProgress = true)
                }
                is Resource.Success -> {
                    configAdapterDropdown(it.data?.genres)
                    updateContent(showProgress = false)
                }
                is Resource.Error -> {
                    updateContent(showProgress = false)
                    Toast.makeText(requireContext(), getString(R.string.error_message), Toast.LENGTH_SHORT).show()
                }
            }
        }
        viewModel.seriesLiveData.observe(viewLifecycleOwner) {
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
                    updateContent(showProgress = false)
                    Toast.makeText(requireContext(), getString(R.string.error_message), Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun configAdapterDropdown(genre: List<Genre>?) {
        genre?.let {
            val adapter = ArrayAdapter(
                requireContext(),
                android.R.layout.simple_dropdown_item_1line,
                it
            )
            binding.autoCompleteGenre.setAdapter(adapter)
        }
    }

    private fun configClickItemDropdown() {
        binding.autoCompleteGenre.setOnItemClickListener { parent, _, position, _ ->
            val genre = parent.getItemAtPosition(position) as Genre
            viewModel.getSeriesByGenre(genre.id)
        }
    }

    override fun onDetailsMediaClicked(mediaType: MediaType, media: Media) {
        openDetailsSeries(mediaType, media)
    }

    private fun openDetailsSeries(mediaType: MediaType, media: Media) {
        val bundle = bundleOf(MEDIA_KEY to media, MEDIA_TYPE_KEY to mediaType)
        findNavController().navigate(R.id.action_genreFragment_to_detailsMovieActivity, bundle)
    }

    override fun updateContent(showProgress: Boolean) {
        binding.seriesProgressBar.isVisible = showProgress
        binding.content.isVisible = !showProgress
    }

    companion object {
        const val MEDIA_KEY = "MEDIA"
        const val MEDIA_TYPE_KEY = "MEDIA_TYPE"
    }
}