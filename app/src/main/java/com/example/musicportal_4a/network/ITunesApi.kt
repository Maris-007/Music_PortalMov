package com.example.musicportal_4a.network

import com.example.musicportal_4a.model.ITunesResponse
import retrofit2.http.GET
import retrofit2.http.Query

//ARCHIVO QUE PERMITE GENERAR LA PETICION AL API


interface ITunesApi {
    @GET("search")
    //METODO PARA BUSCAR EL ALBUM POR GENERO

    fun obtenerAlbumGenero(
        @Query("term") genero : String,
        @Query("country") pais : String,
        @Query("media") media : String,
        @Query("entity") entidad: String,
        @Query("limit") limite : Int,

    ) : retrofit2.Call<ITunesResponse>



}