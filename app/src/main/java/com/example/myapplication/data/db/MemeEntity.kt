package com.example.myapplication.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MemeEntity (
    @PrimaryKey var id: Long,
    var title: String,
    var description: String,
    var isFavorite: Boolean,
    var createdDate: Long,
    var photoUtl: String
)
