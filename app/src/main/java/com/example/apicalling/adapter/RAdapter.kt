package com.example.apicalling.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView

import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.apicalling.R
import com.example.apicalling.model.catergory.Data

class RAdapter(val arrayList: List<Data>) :
    RecyclerView.Adapter<RAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


        val userId: TextView = itemView.findViewById(R.id.UserId)
        val title: TextView = itemView.findViewById(R.id.title)
        val image : ImageView = itemView.findViewById(R.id.imageView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.itemview, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {


        val userdata = arrayList[position]
        holder.userId.text = userdata.Id.toString()
        holder.title.text = userdata.Name
        Glide.with(holder.image.context)
            .load(userdata.Products[0].PictureModels[0].ImageUrl)
            .into(holder.image)
//        println("our picture" + userdata.PictureModels[0].ImageUrl)
//
//        println("Our khela  data" +it.Data[1].Products[0].PictureModels[0].ImageUrl)

    }
}