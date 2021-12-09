package com.reza.presentation.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.reza.domain.usecase.GetMembersUseCase
import com.reza.presentation.mapper.MembersDomainUiModelMapper
import com.reza.presentation.model.MemberUiModel
import kotlinx.coroutines.flow.*
import timber.log.Timber


const val STARTING_PAGE_INDEX = 1

class MembersPagingSource(
    private val getMembersUseCase: GetMembersUseCase,
    private val membersDomainUiMapper: MembersDomainUiModelMapper,
    private val pageSize: Int
) : PagingSource<Int, MemberUiModel>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MemberUiModel> {
        val page = params.key ?: STARTING_PAGE_INDEX
        Timber.e("page: $page and paramKey: ${params.key}")

        return getMembersUseCase.execute(page)
            .map {
                val nexKey = if (it.count() < pageSize)
                    null
                else
                    page + 1

                LoadResult.Page<Int, MemberUiModel>(
                    data = membersDomainUiMapper.toUiModel(it),
                    prevKey = if (page == STARTING_PAGE_INDEX) null else page,
                    nextKey = nexKey
                )
            }.catch { throwable ->
                 LoadResult.Error<Int, MemberUiModel>(throwable)
            }.first()
    }


    /** The refresh key is used for the initial load of the next PagingSource, after invalidation
     * We need to get the previous key (or next key if previous is null) of the page
     * that was closest to the most recently accessed index.
     * Anchor position is the most recently accessed index
     */
    override fun getRefreshKey(state: PagingState<Int, MemberUiModel>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

}