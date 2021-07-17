package com.example.hatch_i.adapter

class DataModel (machinno: Int?, stepday: String?, tset: String?, rhset: String?, fan: String?)
    {
    private var machinno: Int
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

        fun getMachinNo(): Int? {
            return machinno
        }
        fun setMachinNo(name: Int?) {
            machinno = machinno!!
        }
        fun getStepDay(): String? {
            return stepday
        }
        fun setStepDay(year: String?) {
            this.stepday = stepday!!
        }
        fun getTset(): String? {
            return tset
        }
        fun setTset(genre: String?) {
            this.tset = tset!!
        }
        fun getRhSet(): String? {
            return rhset
        }
        fun setRhSet(year: String?) {
            this.rhset = rhset!!
        }
        fun getFan(): String? {
            return fan
        }
        fun setFan(genre: String?) {
            this.fan = fan!!
        }
}
