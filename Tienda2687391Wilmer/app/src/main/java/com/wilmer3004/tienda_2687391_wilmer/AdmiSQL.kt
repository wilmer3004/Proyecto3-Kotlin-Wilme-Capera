package com.wilmer3004.tienda_2687391_wilmer

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteDatabase.CursorFactory
import android.database.sqlite.SQLiteOpenHelper

class AdmiSQL(contexto:Context, nombre:String, cursor: CursorFactory?, version:Int):SQLiteOpenHelper(contexto,nombre,cursor,version) {
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("create table producto (codigoP int primary key, nombre text, precio real);")
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }

}