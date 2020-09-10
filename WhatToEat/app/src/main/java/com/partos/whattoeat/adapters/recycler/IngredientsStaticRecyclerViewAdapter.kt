package com.partos.whattoeat.adapters.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.partos.whattoeat.MyApp
import com.partos.whattoeat.R
import com.partos.whattoeat.models.Ingredient
import kotlinx.android.synthetic.main.row_ingredient_static.view.*

class IngredientsStaticRecyclerViewAdapter (val ingredientsList: ArrayList<Ingredient>) :
    RecyclerView.Adapter<IngredientsStaticViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngredientsStaticViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val cell = inflater.inflate(R.layout.row_ingredient_static, parent, false)
        return IngredientsStaticViewHolder(cell)
    }

    override fun getItemCount(): Int {
        return ingredientsList.size
    }

    override fun onBindViewHolder(holder: IngredientsStaticViewHolder, position: Int) {
        holder.view.row_ingredient_static_name.text = ingredientsList[position].name
        if (ingredientsList[position].amount == 0.0) {
            holder.view.row_ingredient_static_amount.text = ""
        } else {
            holder.view.row_ingredient_static_amount.text = ingredientsList[position].amount.toString()
        }
        holder.view.row_ingredient_static_type.text = ingredientsList[position].type
    }

}

class IngredientsStaticViewHolder(val view: View) : RecyclerView.ViewHolder(view)