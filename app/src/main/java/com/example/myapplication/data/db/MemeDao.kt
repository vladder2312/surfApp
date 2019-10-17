package com.example.myapplication.data.db

import androidx.room.*
import io.reactivex.Observable

@Dao
interface MemeDao {

    @Query("SELECT * FROM MemeEntity")
    fun getAll(): Observable<List<MemeEntity>>

    @Query("SELECT * FROM MemeEntity WHERE id = :id")
    fun findByTitle(id: String): Observable<MemeEntity>

    @Insert
    fun insert(meme: MemeEntity)

    @Insert
    fun insertAll(meme: List<MemeEntity>)

    @Delete
    fun delete(meme: MemeEntity)

    @Update
    fun updateMeme(memes: List<MemeEntity>)

}