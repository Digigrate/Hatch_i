package com.example.hatch_i.model

class DataMachine (title: String?, description: String?, machinenumber: String?)
{
    private var title: String
    private var description: String
    private var machinenumber: String

    init {
        this.title = title!!
        this.description = description!!
        this.machinenumber = machinenumber!!
    }

    fun getTitle(): String? {
        return title
    }
    fun setTitle(titlen: String?) {
        title = title!!
    }
    fun getDescription(): String? {
        return description
    }
    fun setDescription(description: String?) {
        this.description = description!!
    }
    fun getMachinenumber(): String? {
        return machinenumber
    }
    fun setMachinenumber(machinenumber: String?) {
        this.machinenumber = machinenumber!!
    }
}
