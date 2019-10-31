package com.example.myapplication.data.network.models

data class MemeDto (
    val id : Long,
    val title : String,
    val description : String,
    val isFavorite : Boolean,
    val createdDate : String,
    val photoUtl : String
)