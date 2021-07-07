package com.example.hatch_i.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class Login(
    @Expose
    @SerializedName("email")
    val email: String,
    @Expose
    @SerializedName("password")
    val password: String

)