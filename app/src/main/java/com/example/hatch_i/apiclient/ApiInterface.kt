package com.example.hatch_i.apiclient

import com.example.hatch_i.activity.SplashActivity
import com.example.hatch_i.model.HomePageMaster
import com.example.hatch_i.model.Login
import com.example.hatch_i.model.LoginResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface ApiInterface {



    //    @POST("login")
//    fun getLogininfo(): Call<List<Login>>
        @Headers("Content-Type:application/json")
        @POST("login")
        fun login(@Body info: Login): retrofit2.Call<LoginResponse>


    @Headers("Content-Type:application/json","Authorization:Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiIxIiwianRpIjoiZTc1OTJjMjA0MmRiYzMzMTc0NDFhZmI1MTg2YmRkZjJmNmVkMWE3NmRhMWIwODNjMzZmZDQ4Yzg2MWMyMjJmMjkwNDQ1YjhkMzE2ZjJjOWQiLCJpYXQiOjE2MjczOTkyMzYsIm5iZiI6MTYyNzM5OTIzNiwiZXhwIjoxNjU4OTM1MjM2LCJzdWIiOiIxIiwic2NvcGVzIjpbXX0.nqKkUpiiXUrgZUpMjf_SnLH9Sd9MBpfSC-8qYBinlOPDnofr7BiOj8pbcsw5g4-IhabA-eWi6xgE7bFQJRZasmRKyTs0tf1BUvjXJEbbZGT9UxoukgQXDzJ0GW-Bb6X1eDUfgSZvPtnEZ12DU8XSHrYKLmhfDZf2j1QGQMc_t_eWjP9NLc7xwe2emky8kmvz8Y5N0Qac-k_RTweMQva0DU_XHORDFzGwqp8eQ1DeCI5LjqO9rVUJSxBqXIVXaoIfI-JfmpYJHW55UGUeYbv0nzRe3nImomPoAwBFQYcA0nSC_mibbzeRbA6KW7P7cNRgM6V63VLhCNnLCQn3ovCpVgyD9CVZT_Rq4D-6lCrQrc3AMfQwZwqUUVGh8mHyELmrLH31kccJi5Eoez267f0YbvZNy3osfRLe9e-y5Kq6w9rz3TJM0k7zlKiu-L7zWyVAdmwQtqS2ukaeqvttbca2EwzGCZFTFIG4kA8QeVtJxr2d8V5Lj71SfyenmUorpcnlXuxdCkMdm0wHmnZSZSFAWYCnqvOA4osh3JuQxu1OUGLk_oSW1xyoTpyvqwXTAGNlwkKRc3Kb7KJzzZLB_Xu8Q2Xql3zLIvKDx_4VOl2656h1OogNsnogMiDgzD7KaQ9Z2e47EJIp40u80O6_8Fy81cWbFrXnBcJK_fgIZvi1ETA")
    @GET("user/1/device_report")
    fun getHomePageData(): Call<HomePageMaster>




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


