package com.example.hatch_i.adapter

class DataModel (machinno: String?, stepday: String?, tset: String?, rhset: String?, fan: String?)
    {
    private var machinno: String
    private var stepday: String
    private var tset: String
        private var rhset: String
        private var fan: String
    init {
        this.machinno = machinno!!
        this.stepday = stepday!!
        this.tset = tset!!
        this.rhset = rhset!!
        this.fan = fan!!
    }

        fun getMachinNo(): String? {
            return machinno
        }
        fun setMachinNo(name: String?) {
            machinno = name!!
        }
        fun getStepDay(): String? {
            return stepday
        }
        fun setStepDay(year: String?) {
            this.stepday = year!!
        }
        fun getTset(): String? {
            return tset
        }
        fun setTset(genre: String?) {
            this.tset = genre!!
        }
        fun getRhSet(): String? {
            return rhset
        }
        fun setRhSet(year: String?) {
            this.rhset = year!!
        }
        fun getFan(): String? {
            return fan
        }
        fun setFan(genre: String?) {
            this.fan = genre!!
        }
}
