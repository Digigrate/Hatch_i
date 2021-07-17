package com.example.hatch_i.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.example.hatch_i.R
import com.example.hatch_i.model.Content


internal class DetailsAdapter(private var dataList: ArrayList<Content>,
                              private val machineId: Int) :
    RecyclerView.Adapter<DetailsAdapter.MyViewHolder>() {

    internal inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
//        var tv_mdata: TextView = view.findViewById(R.id.tv_mdata)
//        var tv_subdata: TextView = view.findViewById(R.id.tv_subdata)
//        var img_temp: ImageView = view.findViewById(R.id.img_temp)


        var img0: ImageView = view.findViewById(R.id.img0)
        var img1: ImageView = view.findViewById(R.id.img1)
        var img2: ImageView = view.findViewById(R.id.img2)
        var img3: ImageView = view.findViewById(R.id.img3)
        var img4: ImageView = view.findViewById(R.id.img4)
        var img5: ImageView = view.findViewById(R.id.img5)
        var img6: ImageView = view.findViewById(R.id.img6)
        var img7: ImageView = view.findViewById(R.id.img7)
        var img8: ImageView = view.findViewById(R.id.img8)
        var img9: ImageView = view.findViewById(R.id.img9)
        var img10: ImageView = view.findViewById(R.id.img10)
        var img11: ImageView = view.findViewById(R.id.img11)

        var tv_data0: TextView = view.findViewById(R.id.tv_data0)
        var tv_data1: TextView = view.findViewById(R.id.tv_data1)
        var tv_data2: TextView = view.findViewById(R.id.tv_data2)
        var tv_data3: TextView = view.findViewById(R.id.tv_data3)
        var tv_data4: TextView = view.findViewById(R.id.tv_data4)
        var tv_data5: TextView = view.findViewById(R.id.tv_data5)
        var tv_data6: TextView = view.findViewById(R.id.tv_data6)
        var tv_data7: TextView = view.findViewById(R.id.tv_data7)
        var tv_data8: TextView = view.findViewById(R.id.tv_data8)
        var tv_data9: TextView = view.findViewById(R.id.tv_data9)
        var tv_data10: TextView = view.findViewById(R.id.tv_data10)
        var tv_data11: TextView = view.findViewById(R.id.tv_data11)

        var tv_subdata0: TextView = view.findViewById(R.id.tv_subdata0)
        var tv_subdata1: TextView = view.findViewById(R.id.tv_subdata1)
        var tv_subdata2: TextView = view.findViewById(R.id.tv_subdata2)
        var tv_subdata3: TextView = view.findViewById(R.id.tv_subdata3)
        var tv_subdata4: TextView = view.findViewById(R.id.tv_subdata4)
        var tv_subdata5: TextView = view.findViewById(R.id.tv_subdata5)
        var tv_subdata6: TextView = view.findViewById(R.id.tv_subdata6)
        var tv_subdata7: TextView = view.findViewById(R.id.tv_subdata7)
        var tv_subdata8: TextView = view.findViewById(R.id.tv_subdata8)
        var tv_subdata9: TextView = view.findViewById(R.id.tv_subdata9)
        var tv_subdata10: TextView = view.findViewById(R.id.tv_subdata10)
        var tv_subdata11: TextView = view.findViewById(R.id.tv_subdata11)

    }
    @NonNull
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.adapter_details_layout, parent, false)
        return MyViewHolder(itemView)
    }


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val data = dataList[position]

        if(data.id == machineId) {

            //if (position == 0) {
                holder.tv_data0.setText("Set Temp")
                holder.img0.setImageResource(R.drawable.tempnor)
                holder.tv_subdata0.setTextColor(Color.parseColor("#D73729"))
            //} else if (position == 1) {
                holder.tv_data1.setText("M/C Temp")
                holder.img1.setImageResource(R.drawable.tempnor)
                holder.tv_subdata1.setTextColor(Color.parseColor("#D73729"))
            //} else if (position == 2) {
                holder.tv_data2.setText("Set Rh")
                holder.img2.setImageResource(R.drawable.drop)
                holder.tv_subdata2.setTextColor(Color.parseColor("#D73729"))
            //} else if (position == 3) {
                holder.tv_data3.setText("M/C Rh")
                holder.img3.setImageResource(R.drawable.drop)
                holder.tv_subdata3.setTextColor(Color.parseColor("#D73729"))
            //} else if (position == 4) {
                holder.tv_data4.setText("Fan")
                holder.img4.setImageResource(R.drawable.fan)
                holder.tv_subdata4.setTextColor(Color.parseColor("#D73729"))
            //} else if (position == 5) {
                holder.tv_data5.setText("Heater")
                holder.img5.setImageResource(R.drawable.heater)
                holder.tv_subdata5.setTextColor(Color.parseColor("#D73729"))
            //} else if (position == 6) {
                holder.tv_data6.setText("CO2")
                holder.img6.setImageResource(R.drawable.co2)
                holder.tv_subdata6.setTextColor(Color.parseColor("#D73729"))
           // } else if (position == 7) {
                holder.tv_data7.setText("Damper")
                holder.img7.setImageResource(R.drawable.dumper)
                holder.tv_subdata7.setTextColor(Color.parseColor("#D73729"))
           // } else if (position == 8) {
                holder.tv_data8.setText("Cooling Coil")
                holder.img8.setImageResource(R.drawable.coil)
                holder.tv_subdata8.setTextColor(Color.parseColor("#D73729"))
          //  } else if (position == 9) {
                holder.tv_data9.setText("Humidfier")
                holder.img9.setImageResource(R.drawable.humid)
                holder.tv_subdata9.setTextColor(Color.parseColor("#FFFFFFFF"))
                holder.tv_subdata9.setBackgroundColor(Color.parseColor("#FF274F"))
           // } else if (position == 10) {
                holder.tv_data10.setText("Power")
                holder.img10.setImageResource(R.drawable.powwer)
                holder.tv_subdata10.setTextColor(Color.parseColor("#FFFFFFFF"))
                holder.tv_subdata10.setBackgroundColor(Color.parseColor("#03DAC6"))
          //  } else if (position == 11) {
                holder.tv_data11.setText("Door")
                holder.img11.setImageResource(R.drawable.door)
                holder.tv_subdata11.setTextColor(Color.parseColor("#356EAE"))
          //  } else {
             //   holder.img1.setImageResource(R.drawable.temphigh)
           // }

        }
    }
    override fun getItemCount(): Int {
        return dataList.size
    }


}


