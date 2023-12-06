package com.wilmer3004.tienda_2687391_wilmer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast

class Productos : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_productos)


        val arrayAdapter: ArrayAdapter<String>

        val dataLista = findViewById<ListView>(R.id.ListViewProductos)

        // Obtener la lista de productos del intent
        val productosList = intent.getStringArrayListExtra("productosList")

        // Verificar si hay productos para mostrar
        if (productosList != null && productosList.isNotEmpty()) {
            arrayAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, productosList)
            dataLista.adapter = arrayAdapter
        } else {
            // Si no hay productos, puedes mostrar un mensaje o dejar la lista vacía
            Toast.makeText(this, "¡No hay productos disponibles!", Toast.LENGTH_LONG).show()
        }


    }
}