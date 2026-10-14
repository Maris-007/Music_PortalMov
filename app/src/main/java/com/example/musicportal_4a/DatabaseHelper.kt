package com.example.musicportal_4a

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import androidx.core.content.contentValuesOf

class DatabaseHelper(context: Context) : SQLiteOpenHelper(
    context,
    DATABASE_NAME,
    null,
    DATABASE_VERSION,

){
    companion object{
        private const val DATABASE_NAME = "music.db"
        private const val  DATABASE_VERSION = 1
        private const val TABLEUSUARIOS = "usuarios"
        private const val COL_ID = "id"
        private const val COL_NOMBRE = "nombre"
        private const val COL_CORREO = "correo"
        private const val COL_PASSWORD = "password"

    }

    //METODOS PARA CREAR LA BASE DE DATOS
    override fun onCreate(p0: SQLiteDatabase?) {
       val crearTablaUsuarios = """
           CREATE TABLE $TABLEUSUARIOS (
                $COL_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COL_NOMBRE TEXT NOT NULL,
                $COL_CORREO TEXT NOT NULL UNIQUE,
                $COL_PASSWORD TEXT NOT NULL,
           )
       """.trimIndent()

        //CREAR TABLA SQL
        p0?.execSQL(crearTablaUsuarios)

    }

    //METODO PARA ACTUALIZAR LA BASSE DE DATOS
    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        p0?.execSQL("DROP TABLE IF EXISTS $TABLEUSUARIOS")
        onCreate(p0)

    }



    //METODO PARA INSERTAR UN REGISTRO
    fun registrarUsuario(nombre : String, correo : String, password : String): Boolean {

        //Crear variable para que la base de datos e pueda modificar
        val p0 = writableDatabase

        //Crear variable para recibir los datos que te da el usuario
        val datos = ContentValues().apply {
            put(COL_NOMBRE, nombre)
            put(COL_CORREO, correo)
            put(COL_PASSWORD, password)
        }

        //Insertar datos en la tabla
        val resultado = p0.insert(TABLEUSUARIOS, null, datos)
            //1l Verdrecibio -1l no recibioFals

        //Cerrar la conexion con la basede datos
        p0.close()

        //Comparar el valor del resultado de la inserccion y regresarlo como Boolean
        return resultado != -1L
    }




}
