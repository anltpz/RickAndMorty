package com.example.rickandmorty.repository

import com.example.rickandmorty.database.remote.RetrofitService
import javax.inject.Inject

class CharacterRepository @Inject constructor(val service: RetrofitService) {

   suspend fun getAll(alive:String,currentPage:Int)= service.getAllCharacter(alive,currentPage)



}