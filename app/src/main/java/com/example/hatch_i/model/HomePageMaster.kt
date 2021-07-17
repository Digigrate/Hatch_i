package com.example.hatch_i.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class HomePageMaster {

    @SerializedName("content")
    @Expose
    var content: List<Content>? = null
}