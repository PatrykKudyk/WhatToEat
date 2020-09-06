package com.partos.whattoeat.adapters.recycler

import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.partos.whattoeat.MyApp
import com.partos.whattoeat.R
import kotlinx.android.synthetic.main.row_ingredient.view.*

class IngredientsRecyclerViewAdapter :
    RecyclerView.Adapter<IngredientsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngredientsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val cell = inflater.inflate(R.layout.row_ingredient, parent, false)
        return IngredientsViewHolder(cell)
    }

    override fun getItemCount(): Int {
        return MyApp.ingredientsList.size
    }

    override fun onBindViewHolder(holder: IngredientsViewHolder, position: Int) {
        holder.view.row_ingredient_name.setText(MyApp.ingredientsList[position].name)
        if (MyApp.ingredientsList[position].amount == 0.0) {
            holder.view.row_ingredient_amount.setText("")
        } else {
            holder.view.row_ingredient_amount.setText(MyApp.ingredientsList[position].amount.toString())
        }
        holder.view.row_ingredient_type.setText(MyApp.ingredientsList[position].type)

        holder.view.row_ingredient_name.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                MyApp.ingredientsList[position].name =
                    holder.view.row_ingredient_name.text.toString()
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        })
        holder.view.row_ingredient_amount.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                if (holder.view.row_ingredient_amount.text.toString() == "") {
                    MyApp.ingredientsList[position].amount = 0.0
                } else {
                    MyApp.ingredientsList[position].amount =
                        holder.view.row_ingredient_amount.text.toString().toDouble()
                }
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

        })
        holder.view.row_ingredient_type.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                MyApp.ingredientsList[position].type =
                    holder.view.row_ingredient_type.text.toString()
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

        })
        holder.view.row_ingredient_delete.setOnClickListener {
            MyApp.ingredientsList.removeAt(position)
            notifyItemRemoved(position)
            notifyItemRangeChanged(position, MyApp.ingredientsList.size)
        }
    }

}

class IngredientsViewHolder(val view: View) : RecyclerView.ViewHolder(view)