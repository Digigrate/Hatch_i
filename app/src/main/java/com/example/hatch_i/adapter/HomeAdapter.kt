package com.example.hatch_i.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.example.hatch_i.R
import com.example.hatch_i.activity.MainActivity
import com.example.hatch_i.model.Content
import com.example.hatch_i.storageHelpers.PreferenceHelper


internal class HomeAdapter(
    private var dataList: ArrayList<Content>
) :
    RecyclerView.Adapter<HomeAdapter.MyViewHolder>() {
    internal inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        var machinno: TextView = view.findViewById(R.id.machineno)
        var stepday: TextView = view.findViewById(R.id.stepday)
        var tset: TextView = view.findViewById(R.id.tset)
        var rhset: TextView = view.findViewById(R.id.rhset)
        var fan: TextView = view.findViewById(R.id.fan)

    }
    @NonNull
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.text_row_item, parent, false)
        return MyViewHolder(itemView)
    }
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val data = dataList[position]
        holder.machinno.text = data.id.toString()
        holder.stepday.text = "00"
        holder.tset.text = data.set_temp+"("+data.machine_temp+")"
        holder.rhset.text = data.set_humidity+"("+data.machine_humidity+")"
        holder.fan.text = data.fan+"%"

        holder.itemView.setOnClickListener(View.OnClickListener {
            val mainActivity: MainActivity? =
                it.context as MainActivity?
            mainActivity!!.setFragment()
            var id =  data.id
            var mname =  data.name
            PreferenceHelper.setIntegerPreference(it.context, "machineId", id!!)
            PreferenceHelper.setStringPreference(it.context, "machinename", mname!!)

        })
    }
    override fun getItemCount(): Int {
        return dataList.size
    }
}


