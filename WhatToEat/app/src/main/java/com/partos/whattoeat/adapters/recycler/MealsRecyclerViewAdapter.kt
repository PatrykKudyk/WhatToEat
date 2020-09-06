package com.partos.whattoeat.adapters.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.partos.whattoeat.R
import com.partos.whattoeat.models.Meal
import kotlinx.android.synthetic.main.row_meal.view.*

class MealsRecyclerViewAdapter(var mealsList: ArrayList<Meal>) :
    RecyclerView.Adapter<MealsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val cell = inflater.inflate(R.layout.row_meal, parent, false)
        return MealsViewHolder(cell)
    }

    override fun getItemCount(): Int {
        return mealsList.size
    }

    override fun onBindViewHolder(holder: MealsViewHolder, position: Int) {
        holder.view.row_meal_name.text = mealsList[position].name
    }

}

class MealsViewHolder(val view: View) : RecyclerView.ViewHolder(view)