package com.example.myapplication.models
import com.google.gson.annotations.SerializedName

data class MemeDto (

    @SerializedName("id")
    val id : Integer,

    @SerializedName("title")
    val title : String,

    @SerializedName("description")
    val description : String,

    @SerializedName("isFavorite")
    val isFavorite : Boolean,

    @SerializedName("createdDate")
    val createdDate : String,

    @SerializedName("photoUrl")
    val photoUtl : String

)