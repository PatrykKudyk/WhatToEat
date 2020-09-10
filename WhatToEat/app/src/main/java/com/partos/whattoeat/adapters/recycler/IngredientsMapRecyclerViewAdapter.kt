package com.partos.whattoeat.adapters.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.partos.whattoeat.R
import com.partos.whattoeat.models.IngredientExtended
import kotlinx.android.synthetic.main.row_ingredient_extended.view.*

class IngredientsMapRecyclerViewAdapter (val ingredientsList: ArrayList<IngredientExtended>) :
    RecyclerView.Adapter<IngredientsMapViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngredientsMapViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val cell = inflater.inflate(R.layout.row_ingredient_extended, parent, false)
        return IngredientsMapViewHolder(cell)
    }

    override fun getItemCount(): Int {
        return ingredientsList.size
    }

    override fun onBindViewHolder(holder: IngredientsMapViewHolder, position: Int) {
        holder.view.row_ingredient_extended_name.text = ingredientsList[position].name
        var amountString = ""
        for (amount in ingredientsList[position].amounts){
            amountString += amount.amount.toString() + " " + amount.type + "\n"
        }
        holder.view.row_ingredient_extended_amounts.text = amountString
    }

}

class IngredientsMapViewHolder(val view: View) : RecyclerView.ViewHolder(view)