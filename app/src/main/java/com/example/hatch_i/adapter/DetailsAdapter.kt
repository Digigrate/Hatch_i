package com.example.hatch_i.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.example.hatch_i.adapter.DetailsModel
import com.example.hatch_i.R


internal class DetailsAdapter(private var detailsList: List<DetailsModel>) :
    RecyclerView.Adapter<DetailsAdapter.MyViewHolder>() {
    internal inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var tv_mdata: TextView = view.findViewById(R.id.tv_mdata)
        var tv_subdata: TextView = view.findViewById(R.id.tv_subdata)
        var img_temp: ImageView = view.findViewById(R.id.img_temp)


    }
    @NonNull
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.adapter_details_layout, parent, false)
        return MyViewHolder(itemView)
    }


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val data = detailsList[position]
        holder.tv_mdata.text = data.getMachinNo()
        holder.tv_subdata.text = data.getStepDay()

        if(position==0){
            holder.img_temp.setImageResource(R.drawable.tempnor)
            holder.tv_subdata.setTextColor(Color.parseColor("#D73729"))
        }else if(position==1){
            holder.img_temp.setImageResource(R.drawable.tempnor)
            holder.tv_subdata.setTextColor(Color.parseColor("#D73729"))
        }else if(position==2){
            holder.img_temp.setImageResource(R.drawable.drop)
            holder.tv_subdata.setTextColor(Color.parseColor("#D73729"))
        }else if(position==3){
            holder.img_temp.setImageResource(R.drawable.drop)
            holder.tv_subdata.setTextColor(Color.parseColor("#D73729"))
        }else if(position==4){
            holder.img_temp.setImageResource(R.drawable.fan)
            holder.tv_subdata.setTextColor(Color.parseColor("#D73729"))
        }else if(position==5){
            holder.img_temp.setImageResource(R.drawable.heater)
            holder.tv_subdata.setTextColor(Color.parseColor("#D73729"))
        }else if(position==6){
            holder.img_temp.setImageResource(R.drawable.co2)
            holder.tv_subdata.setTextColor(Color.parseColor("#D73729"))
        }else if(position==7){
            holder.img_temp.setImageResource(R.drawable.dumper)
            holder.tv_subdata.setTextColor(Color.parseColor("#D73729"))
        }else if(position==8){
            holder.img_temp.setImageResource(R.drawable.coil)
            holder.tv_subdata.setTextColor(Color.parseColor("#D73729"))
        }else if(position==9){
            holder.img_temp.setImageResource(R.drawable.humid)
            holder.tv_subdata.setTextColor(Color.parseColor("#FFFFFFFF"))
            holder.tv_subdata.setBackgroundColor(Color.parseColor("#FF274F"))
        }else if(position==10){
            holder.img_temp.setImageResource(R.drawable.powwer)
            holder.tv_subdata.setTextColor(Color.parseColor("#FFFFFFFF"))
            holder.tv_subdata.setBackgroundColor(Color.parseColor("#03DAC6"))
        }else if(position==11){
            holder.img_temp.setImageResource(R.drawable.door)
            holder.tv_subdata.setTextColor(Color.parseColor("#356EAE"))
        }else{
            holder.img_temp.setImageResource(R.drawable.temphigh)
        }


    }
    override fun getItemCount(): Int {
        return detailsList.size
    }


}


