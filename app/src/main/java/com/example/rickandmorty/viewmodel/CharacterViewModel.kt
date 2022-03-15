package com.example.rickandmorty.viewmodel

import androidx.lifecycle.*
import androidx.paging.*
import com.example.rickandmorty.paging.RickMortyPagingSource
import com.example.rickandmorty.model.Character
import com.example.rickandmorty.repository.CharacterRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CharacterViewModel @Inject constructor(private val repository: CharacterRepository) :
    ViewModel() {

    private val _resp = MutableLiveData<Character>()
    val resp: LiveData<Character>
        get() = _resp

  private  val query = MutableLiveData<String>()


    val listData = query.switchMap {alive->
        Pager(PagingConfig(pageSize = 10, maxSize = 30, enablePlaceholders = false, prefetchDistance = 5, initialLoadSize = 10)) {
            RickMortyPagingSource(alive,repository)
        }.liveData.cachedIn(viewModelScope)

    }

    fun setQuery(alive: String?="") {
        query.value=alive!!
    }


}