package com.wilmer3004.tienda_2687391_wilmer

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        EditText
        val codigo = findViewById<EditText>(R.id.editTextCodigoP)
        val nombre = findViewById<EditText>(R.id.editTextNombreP)
        val precio = findViewById<EditText>(R.id.editTextPrecioP)

//        Botones
        val registrar = findViewById<Button>(R.id.buttonRegistrar)
        val consultar = findViewById<Button>(R.id.buttonConsultar)
        val editar = findViewById<Button>(R.id.buttonEditar)
        val eliminar = findViewById<Button>(R.id.buttonEliminar)
        val productos = findViewById<Button>(R.id.buttonProductos)

//        Evento Registrar
        registrar.setOnClickListener{
            val adminBD = AdmiSQL(this,"MiTienda",null,1)
            val bd = adminBD.writableDatabase
            val registro = ContentValues()
            registro.put("codigoP",codigo.text.toString().toInt())
            registro.put("nombre",nombre.text.toString())
            registro.put("precio",precio.text.toString().toFloat())

            bd.insert("producto",null,registro)
            bd.close()
            codigo.setText("")
            nombre.setText("")
            precio.setText("")
            Toast.makeText(this,"¡Producto Registrado!",Toast.LENGTH_LONG).show()
        }

//        Evento  Consultar

        consultar.setOnClickListener{
            val adminBD = AdmiSQL(this,"MiTienda",null,1)
            val bd = adminBD.writableDatabase
            val consulta = bd.rawQuery("SELECT nombre,precio FROM producto WHERE codigoP = ${codigo.text.toString().toInt()}",null)
            if (consulta.moveToFirst()){
                nombre.setText(consulta.getString(0))
                precio.setText(consulta.getString(1))
            }
            else{
                Toast.makeText(this,"¡Producto NO encontrado!",Toast.LENGTH_LONG).show()
            }
            bd.close()

        }

//        Evento Editar

        editar.setOnClickListener{
            val adminBD = AdmiSQL(this,"MiTienda",null,1)
            val bd = adminBD.writableDatabase
            val registro = ContentValues()
            registro.put("codigoP",codigo.text.toString().toInt())
            registro.put("nombre",nombre.text.toString())
            val editar= bd.update("producto",registro,"codigoP=${codigo.text.toString().toInt()}",null)
            bd.close()
            codigo.setText("")
            nombre.setText("")
            precio.setText("")
            if(editar == 1){
                Toast.makeText(this,"¡Producto Actualizado!",Toast.LENGTH_LONG).show()
            }
            else{
                Toast.makeText(this,"¡Producto NO Existe!",Toast.LENGTH_LONG).show()
            }
        }

//        Evento Eliminar

        eliminar.setOnClickListener{
            val adminBD = AdmiSQL(this,"MiTienda",null,1)
            val bd = adminBD.writableDatabase
            val eliminar = bd.delete("producto","codigoP=${codigo.text.toString().toInt()}",null)
            bd.close()
            codigo.setText("")
            nombre.setText("")
            precio.setText("")
            if(eliminar == 1){

                Toast.makeText(this,"¡Producto Eliminado!",Toast.LENGTH_LONG).show()
            }
            else{
                Toast.makeText(this,"¡Producto NO Existe!",Toast.LENGTH_LONG).show()
            }

        }

        productos.setOnClickListener {
            val adminBD = AdmiSQL(this, "MiTienda", null, 1)
            val bd = adminBD.writableDatabase
            val consulta = bd.rawQuery("SELECT nombre, precio FROM producto", null)

            val productosList = mutableListOf<String>()

            if (consulta.moveToFirst()) {
                do {
                    val nombreProducto = consulta.getString(0)
                    val precioProducto = consulta.getString(1)
                    productosList.add("$nombreProducto - $precioProducto")
                } while (consulta.moveToNext())

                // Cerrar el cursor después de usarlo
                consulta.close()

                // Abrir la nueva actividad y pasar la lista de productos
                val intent = Intent(this, Productos::class.java)
                intent.putStringArrayListExtra("productosList", ArrayList(productosList))
                startActivity(intent)
            } else {
                Toast.makeText(this, "¡No hay productos disponibles!", Toast.LENGTH_LONG).show()
            }

            bd.close()
        }




    }
}