package com.example.rickandmorty.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.rickandmorty.model.Results
import com.example.rickandmorty.repository.CharacterRepository

class RickMortyPagingSource(var alive:String,private val repository: CharacterRepository) :
    PagingSource<Int, Results>() {

     val UNSPLASH_STARTING_PAGE_INDEX = 1

    override fun getRefreshKey(state: PagingState<Int, Results>): Int? {
      return null
      }


    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Results> {
        return try {
            val currentPage = params.key ?: UNSPLASH_STARTING_PAGE_INDEX
            val response = repository.getAll(alive,currentPage)
            val responseData = mutableListOf<Results>()
            val data = response.body()?.results ?: emptyList()
            responseData.addAll(data)

            LoadResult.Page(
                data = responseData,
                prevKey =  if (currentPage == UNSPLASH_STARTING_PAGE_INDEX) null else currentPage - 1,
                nextKey = if (data.isEmpty()) null else currentPage+1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }

    }


}