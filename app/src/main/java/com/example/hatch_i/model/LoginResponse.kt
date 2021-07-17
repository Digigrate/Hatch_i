package com.example.hatch_i.model

import com.google.gson.annotations.SerializedName



data class LoginResponse(
    var token: String? = null,
    var message: String? = null,
    var success: String? = null,
    val user : User? = null
)