package com.example.myapplication.data.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [MemeEntity::class], version = 1)
abstract class MemeDatabase : RoomDatabase() {
    abstract val memeDao: MemeDao
}