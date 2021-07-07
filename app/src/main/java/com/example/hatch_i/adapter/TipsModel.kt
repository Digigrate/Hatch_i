package com.example.hatch_i.adapter

class TipsModel(tipsname: String?)
{
    private var tipsname: String

    init {
        this.tipsname = tipsname!!

    }

    fun getTipsName(): String? {
        return tipsname
    }
    fun setTipsName(name: String?) {
        tipsname = name!!
    }

}
