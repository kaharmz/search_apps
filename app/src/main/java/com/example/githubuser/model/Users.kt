package com.example.githubuser.model

import com.google.gson.annotations.SerializedName

data class Users(
    @SerializedName("items")
    val list: List<Items>,

    @SerializedName("total_count")
    val total: Int? = null
)