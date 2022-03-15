package com.example.rickandmorty.database.remote
import androidx.lifecycle.LiveData
import com.example.rickandmorty.model.Character
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitService {

    @GET("character")
     suspend  fun getAllCharacter(
        @Query("status")  status:String?,
        @Query("page") page: Int,

    )
     :Response<Character>


}

