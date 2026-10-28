package com.example.musicportal_4a.network

import com.google.gson.Gson
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

//ARCHIVO PARA CONSTRUIR TODO EL ENLACE DEL API

object RetrofitClient {

    //Colocar la base del url
    private const val BASE_URL = "https://itunes.apple.com/"

    //Construir la url completa
    val api : ITunesApi by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ITunesApi::class.java)

    }

}