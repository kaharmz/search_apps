package com.example.githubuser.model

import com.google.gson.annotations.SerializedName

data class Items(

    @SerializedName("login")
    val login: String? = null,

    @SerializedName("avatar_url")
    val avatar: String? = null

)