package com.example.myapplication.Models

import com.google.gson.annotations.SerializedName

class MemeDto {

    @SerializedName("id")
    var id = 0

    @SerializedName("title")
    var title = ""

    @SerializedName("description")
    var description = ""

    @SerializedName("isFavorite")
    var isFavorite = false

    @SerializedName("createdDate")
    var createdDate = 0

    @SerializedName("photoUrl")
    var photoUtl = ""

}