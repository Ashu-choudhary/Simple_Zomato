package com.example.foodapplication.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.foodapplication.R
import com.example.foodapplication.model.Food
import kotlin.collections.ArrayList

class DashboardRecyclerAdapter(val context:Context, val itemList: ArrayList<Food>) : RecyclerView.Adapter<DashboardRecyclerAdapter.DashboardViewHolder> () {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DashboardViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycle_dashboard_single_row,parent,false)

        return DashboardViewHolder(view)
    }
    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: DashboardViewHolder, position: Int) {

        val food = itemList[position]
        holder.txtFoodName.text = food.foodName
        holder.txtFoodPrice.text = food.foodPrice
        holder.txtFoodRating.text = food.foodRating
        holder.txtFoodImage.setImageResource(food.foodImage)

        holder.llcontent.setOnClickListener {
            Toast.makeText(context, "Clicked on ${holder.txtFoodName.text}", Toast.LENGTH_SHORT).show()
        }
    }
    class DashboardViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val txtFoodName : TextView = view.findViewById(R.id.txtFoodName)
        val txtFoodPrice : TextView = view.findViewById(R.id.txtFoodPrice)
        val txtFoodRating : TextView = view.findViewById(R.id.txtFoodRating)
        val txtFoodImage : ImageView = view.findViewById(R.id.imgFoodImage)
        val llcontent : LinearLayout = view.findViewById(R.id.llcontent)
    }


}