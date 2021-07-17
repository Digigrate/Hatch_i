package com.example.hatch_i.apiclient

import android.util.Log
import com.example.hatch_i.model.Login
import com.example.hatch_i.model.LoginResponse
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.Headers
import retrofit2.http.POST

interface ApiInterface {
//    @POST("login")
//    fun getLogininfo(): Call<List<Login>>
        @Headers("Content-Type:application/json")
        @POST("login")
        fun login(@Body info: Login): retrofit2.Call<LoginResponse>


    class RetrofitInstance {
        companion object {
            val BASE_URL: String = "http://poultryi.com/index.php/api/v1/"

            val interceptor: HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
                this.level = HttpLoggingInterceptor.Level.BODY
            }

            val client: OkHttpClient = OkHttpClient.Builder().apply {
                this.addInterceptor(interceptor)
            }.build()
            fun getRetrofitInstance(): Retrofit {
                return Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
        }
    }
}