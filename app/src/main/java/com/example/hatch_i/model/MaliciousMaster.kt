package com.example.hatch_i.model

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

class MaliciousMaster {
    @SerializedName("success")
    @Expose
    var success: String? = null



    @SerializedName("data")
    @Expose
    var data: List<Packages>? = null


    @SerializedName("cmsData")
    @Expose
    var cmsData: String? = null


    data class Packages (
        @SerializedName("packagename")
        @Expose
        var packagename: String? = null,
        @SerializedName("appname")
        @Expose
        var appname: String? = null)

}