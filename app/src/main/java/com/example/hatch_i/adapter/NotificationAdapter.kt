package com.example.hatch_i.adapter


import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.example.hatch_i.adapter.NotificationModel
import com.example.hatch_i.R


internal class NotificationAdapter(private var notificationsList: List<NotificationModel>) :
    RecyclerView.Adapter<NotificationAdapter.MyViewHolder>() {
    internal inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var machname: TextView = view.findViewById(R.id.machname)
        var machninstemptext: TextView = view.findViewById(R.id.machninstemptext)
        var machntemp: TextView = view.findViewById(R.id.machntemp)
        var timemach: TextView = view.findViewById(R.id.timemach)
        var datemach: TextView = view.findViewById(R.id.datemach)
        var tv_summer: TextView = view.findViewById(R.id.tv_summer)
        var img_temp: ImageView = view.findViewById(R.id.img_temp)


    }
    @NonNull
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.new_notification_layout, parent, false)
        return MyViewHolder(itemView)
    }


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val data = notificationsList[position]
        holder.machname.text = data.getMachinName()
        holder.machninstemptext.text = data.getMachineTemp()
        holder.machntemp.text = data.getMachTemp()
        if(position==0){
            holder.machntemp.setTextColor(Color.parseColor("#0071CA"))
            holder.tv_summer.text = "Summer"
            holder.img_temp.setImageResource(R.drawable.temperature)
        }else if(position==1){
            holder.machntemp.setTextColor(Color.parseColor("#FFD700"))
            holder.tv_summer.text = "Low Temp"
            holder.img_temp.setImageResource(R.drawable.templow)
        }else if(position==2){
            holder.machntemp.setTextColor(Color.parseColor("#ff724f"))
            holder.tv_summer.text = "High Temp"
            holder.img_temp.setImageResource(R.drawable.temphigh)
        }else{
            holder.machntemp.setTextColor(Color.parseColor("#0071CA"))
        }

        holder.timemach.text = data.getTimeMech()
        holder.datemach.text = data.getDateMech()

    }
    override fun getItemCount(): Int {
        return notificationsList.size
    }


}


