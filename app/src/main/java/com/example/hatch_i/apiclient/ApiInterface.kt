package com.example.hatch_i.apiclient

import com.example.hatch_i.adapter.DataModel
import com.example.hatch_i.model.Login
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiInterface {
    @POST("login")
    fun getLogininfo(): Call<List<Login>>
}