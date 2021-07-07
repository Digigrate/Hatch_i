package com.example.hatch_i.adapter

import android.graphics.Color
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.example.hatch_i.adapter.TipsModel
import com.example.hatch_i.R


internal class TipsAdapter(private var tipsList: List<TipsModel>) :
    RecyclerView.Adapter<TipsAdapter.MyViewHolder>() {
    var one_click: Boolean = false
    internal inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var tipsname: AppCompatTextView = view.findViewById(R.id.tipsname)
        var img_tips: AppCompatImageView = view.findViewById(R.id.img_tips)
        var forwardimg: AppCompatImageView = view.findViewById(R.id.forwardimg)

        var tv_details: TextView = view.findViewById(R.id.tv_details)

    }
    @NonNull
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.text_row_item_tips, parent, false)
        return MyViewHolder(itemView)
    }
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val data = tipsList[position]
        holder.tipsname.text = data.getTipsName()
        holder.forwardimg.setImageResource(R.drawable.ic_baseline_keyboard_arrow_down_24)

        if(position==0){
            holder.img_tips.setImageResource(R.drawable.generaltips)
        }else if(position==1){
            holder.img_tips.setImageResource(R.drawable.egg1)
        }else if(position==2){
            holder.img_tips.setImageResource(R.drawable.preincubation)
        }else if(position==3){
            holder.img_tips.setImageResource(R.drawable.postincubation)
        }else if(position==4){
            holder.img_tips.setImageResource(R.drawable.egg)
        }else if(position==5){
            holder.img_tips.setImageResource(R.drawable.postincubation)
        }else if(position==6){
            holder.img_tips.setImageResource(R.drawable.booking)
        }else{
            holder.img_tips.setImageResource(R.drawable.generaltips)
        }

        holder.forwardimg.setOnClickListener(View.OnClickListener {
          //  if(position==0) {
            if (!one_click) {
                one_click = true
                holder.tv_details.visibility = View.VISIBLE
                holder.tv_details.setText(R.string.details)
            } else {
                one_click = false
                holder.tv_details.visibility = View.GONE
            }
           // }

        })


    }
    override fun getItemCount(): Int {
        return tipsList.size
    }
}


