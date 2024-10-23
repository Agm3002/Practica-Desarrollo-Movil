package com.unison.appproductos.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.unison.appproductos.model.Producto
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductsDatabaseDao {

    @Query("SELECT * FROM productos")
    fun getProducts(): Flow<List<Producto>>

    @Query("SELECT * FROM productos WHERE codigo = :codigo")
    fun getProductById(codigo: Int): Flow<Producto>

    @Insert(entity = Producto::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun addProduct(product: Producto)

    @Update(entity = Producto::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateProduct(product: Producto)

    @Delete
    suspend fun deleteProduct(product: Producto)
}