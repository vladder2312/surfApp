package com.example.myapplication.data.models

data class MemeDto (
    val id : Long,
    val title : String,
    val description : String,
    val isFavorite : Boolean,
    val createdDate : String,
    val photoUtl : String
)