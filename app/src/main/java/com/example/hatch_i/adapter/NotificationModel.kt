package com.example.hatch_i.adapter

class NotificationModel (machinname: String?, machninstemptext: String?, machntemp: String?, datemach: String?, timemach: String?)
{
    private var machinname: String
    private var machninstemptext: String
    private var machntemp: String
    private var timemach: String
    private var datemach: String
    init {
        this.machinname = machinname!!
        this.machninstemptext = machninstemptext!!
        this.machntemp = machntemp!!
        this.timemach = timemach!!
        this.datemach = datemach!!
    }

    fun getMachinName(): String? {
        return machinname
    }
    fun setMachinName(name: String?) {
        machinname = machinname!!
    }
    fun getMachineTemp(): String? {
        return machninstemptext
    }
    fun setMachineTemp(year: String?) {
        this.machninstemptext = machninstemptext!!
    }
    fun getMachTemp(): String? {
        return machntemp
    }
    fun setMachTemp(genre: String?) {
        this.machntemp = machntemp!!
    }
    fun getTimeMech(): String? {
        return timemach
    }
    fun setTimeMech(year: String?) {
        this.timemach = timemach!!
    }
    fun getDateMech(): String? {
        return datemach
    }
    fun setDateMech(genre: String?) {
        this.datemach = datemach!!
    }
}
