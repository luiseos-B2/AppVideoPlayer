package com.freecast.thatmovieapp.home.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.freecast.thatmovieapp.home.domain.model.Media
import com.freecast.thatmovieapp.home.domain.model.MediaType
class MediaPagingSource (
    private val mediaType: MediaType,
    private val mediaDataSource: MediaDataSource,
    private val mediaId: Int? = null
): PagingSource<Int, Media>() {

    override fun getRefreshKey(state: PagingState<Int, Media>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Media> {
        return try {
            val nextPageNumber = params.key ?: 1
            val mediaList = mediaDataSource.getMediaData(mediaType, nextPageNumber, mediaId)
            LoadResult.Page(
                data = mediaList.results,
                prevKey = if (nextPageNumber == 1) null else nextPageNumber - 1,
                nextKey = if (nextPageNumber == mediaList.totalPages) null else nextPageNumber + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

}