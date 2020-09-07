package com.partos.whattoeat.adapters.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.partos.whattoeat.R
import com.partos.whattoeat.db.DataBaseHelper
import com.partos.whattoeat.models.Ingredient
import kotlinx.android.synthetic.main.row_ingredient_edit.view.*

class IngredientsEditRecyclerViewAdapter(val ingredientsList: ArrayList<Ingredient>) :
    RecyclerView.Adapter<IngredientsEditViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngredientsEditViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val cell = inflater.inflate(R.layout.row_ingredient_edit, parent, false)
        return IngredientsEditViewHolder(cell)
    }

    override fun getItemCount(): Int {
        return ingredientsList.size
    }

    override fun onBindViewHolder(holder: IngredientsEditViewHolder, position: Int) {
        val constraintNormal = holder.view.row_ingredient_show_constraint_normal
        val constraintEdit = holder.view.row_ingredient_show_constraint_edit
        val constraintDelete = holder.view.row_ingredient_show_constraint_delete
        val db = DataBaseHelper(holder.view.context)

        constraintNormal.visibility = View.VISIBLE
        constraintEdit.visibility = View.GONE
        constraintDelete.visibility = View.GONE

        holder.view.row_ingredient_show_name.text = ingredientsList[position].name
        holder.view.row_ingredient_show_amount.text = ingredientsList[position].amount.toString()
        holder.view.row_ingredient_show_type.text = ingredientsList[position].type

        holder.view.row_ingredient_show_edit.setOnClickListener {
            constraintNormal.visibility = View.GONE
            constraintEdit.visibility = View.VISIBLE
            holder.view.row_ingredient_show_edit_name.setText(holder.view.row_ingredient_show_name.text)
            holder.view.row_ingredient_show_edit_amount.setText(holder.view.row_ingredient_show_amount.text)
            holder.view.row_ingredient_show_edit_type.setText(holder.view.row_ingredient_show_type.text)
        }

        holder.view.row_ingredient_show_save.setOnClickListener {
            constraintNormal.visibility = View.VISIBLE
            constraintEdit.visibility = View.GONE
            holder.view.row_ingredient_show_name.text =
                holder.view.row_ingredient_show_edit_name.text
            holder.view.row_ingredient_show_amount.text =
                holder.view.row_ingredient_show_edit_amount.text
            holder.view.row_ingredient_show_type.text =
                holder.view.row_ingredient_show_edit_type.text
            ingredientsList[position].name =
                holder.view.row_ingredient_show_edit_name.text.toString()
            ingredientsList[position].amount =
                holder.view.row_ingredient_show_edit_amount.text.toString().toDouble()
            ingredientsList[position].type =
                holder.view.row_ingredient_show_edit_type.text.toString()
            db.updateIngredient(ingredientsList[position])
        }

    }

}

class IngredientsEditViewHolder(val view: View) : RecyclerView.ViewHolder(view)