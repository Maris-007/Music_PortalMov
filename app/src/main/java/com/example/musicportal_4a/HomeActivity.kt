package com.example.musicportal_4a

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.example.musicportal_4a.model.ITunesResponse
import com.example.musicportal_4a.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeActivity : AppCompatActivity() {

    private lateinit var tvResultado : TextView
    private lateinit var imgAlbum : ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_home)

        tvResultado  = findViewById(R.id.tvResultado)
        imgAlbum = findViewById(R.id.imgAlbum)
        obtenerAlbum()
    }
    //Metodo para cargar los albums
    private fun obtenerAlbum() {
        RetrofitClient .api.obtenerAlbumGenero(
            genero = "pop",
            pais = "MX",
            media = "music",
            entidad = "album",
            limite = 1,
        ).enqueue(object : Callback<ITunesResponse> {

            //metodo olbigatorio para el funcionamiento
            //Metodo para verificar si la respuesta es correcta
            override fun onResponse(
                call: Call<ITunesResponse?>,
                response: Response<ITunesResponse?>
            ) {
                //variable que obtenga los resultados del API
                val albumes = response.body()?.results

                if(!albumes.isNullOrEmpty() ) {
                    //Variable para guardar la informacion de cada respuesta obtenida del API
                    val album = albumes[0]


                    //Mostrar texto infromacion del API en un TEXTView
                    tvResultado.text = "Nombre del album: ${album.collectionName} \n\n" +
                            "Artista: ${album.artistName}"


                    //Mostrar imagenes desde el API
                    Glide.with(this@HomeActivity)
                        .load(album.obtenerImagenGrande())
                        .into(imgAlbum)

                } else {
                    Toast.makeText(this@HomeActivity, "No hay resultados", Toast.LENGTH_SHORT).show()
                }
            }

            //Metodo para verificar si hay respuesta
            override fun onFailure(call: Call<ITunesResponse?>, t: Throwable) {
                Toast.makeText(this@HomeActivity, "Error en el API", Toast.LENGTH_SHORT).show()
            }
        })
    }
}