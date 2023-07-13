package com.example.suitmedia.data.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.suitmedia.data.response.User
import com.example.suitmedia.data.retrofit.ApiService

class UserPagingSource(private val apiService: ApiService) :
    PagingSource<Int, User>() {

    private companion object {
        const val INITIAL_PAGE_INDEX = 1
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, User> {
        return try {
            val page = params.key ?: INITIAL_PAGE_INDEX

            val responseData = apiService.getUsers(
                page,
                params.loadSize
            )

            val userList = responseData.data
            LoadResult.Page(
                data = userList,
                prevKey = if (page > 1) page - 1 else null,
                nextKey = if (userList.isNullOrEmpty()) null else page + 1
            )
        } catch (exception: Exception) {
            return LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, User>): Int? {
        return null
    }
}