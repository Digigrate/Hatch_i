package com.example.hatch_i.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class HomePageMaster {



    @SerializedName("status")
    @Expose
    var status: String? = null

    @SerializedName("report")
    @Expose
    var content: List<Content>? = null
}