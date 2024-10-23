package com.unison.appproductos.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.unison.appproductos.model.Producto

@Database(
    entities = [Producto::class],
    version = 1,
    exportSchema = false)
abstract class ProductoDatabase : RoomDatabase() {
    abstract fun productosDao(): ProductsDatabaseDao
}