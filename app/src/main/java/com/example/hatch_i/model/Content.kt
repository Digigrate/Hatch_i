package com.example.hatch_i.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Content {


    @SerializedName("id")
    @Expose
    var id: Int? = null

    @SerializedName("name")
    @Expose
    var name: String? = null

    @SerializedName("device_id")
    @Expose
    var device_id: String? = null

    @SerializedName("set_temp")
    @Expose
    var set_temp: String? = null

    @SerializedName("machine_temp")
    @Expose
    var machine_temp: String? = null

    @SerializedName("set_humidity")
    @Expose
    var set_humidity: String? = null

    @SerializedName("machine_humidity")
    @Expose
    var machine_humidity: String? = null

    @SerializedName("fan")
    @Expose
    var fan: String? = null

    @SerializedName("sensor_id")
    @Expose
    var sensor_id: String? = null

    @SerializedName("ip")
    @Expose
    var ip: String? = null

    @SerializedName("heater")
    @Expose
    var heater: String? = null

    @SerializedName("co2")
    @Expose
    var co2: String? = null

    @SerializedName("damper")
    @Expose
    var damper: String? = null

    @SerializedName("cooling_coil")
    @Expose
    var cooling_coil: String? = null

    @SerializedName("humidifier")
    @Expose
    var humidifier: String? = null

    @SerializedName("power")
    @Expose
    var power: String? = null

    @SerializedName("door")
    @Expose
    var door: String? = null

    @SerializedName("created_at")
    @Expose
    var created_at: String? = null

    @SerializedName("updated_at")
    @Expose
    var updated_at: String? = null








}
