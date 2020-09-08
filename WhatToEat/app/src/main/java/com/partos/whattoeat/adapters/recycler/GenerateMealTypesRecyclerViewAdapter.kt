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
import kotlinx.android.synthetic.main.row_meal_type.view.*

class GenerateMealTypesRecyclerViewAdapter(var allowDuplicates: Boolean) :
    RecyclerView.Adapter<GenerateMealTypesViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenerateMealTypesViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val cell = inflater.inflate(R.layout.row_meal_type, parent, false)
        return GenerateMealTypesViewHolder(cell)
    }

    override fun getItemCount(): Int {
        return MyApp.typesList.size
    }

    override fun onBindViewHolder(holder: GenerateMealTypesViewHolder, position: Int) {
        val constraintDuplicates = holder.view.row_meal_type_constraint_duplicates
        val constraintNoDuplicates = holder.view.row_meal_type_constraint_no_duplicates
        if (allowDuplicates) {
            constraintDuplicates.visibility = View.VISIBLE
            constraintNoDuplicates.visibility = View.GONE
            holder.view.row_meal_type_name2.text = MyApp.typesList[position].name
            holder.view.row_meal_type_wanted2.setText(MyApp.typesList[position].wanted.toString())
            holder.view.row_meal_type_wanted2.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(p0: Editable?) {
                    if (holder.view.row_meal_type_wanted2.text.toString() == "") {
                        MyApp.typesList[position].wanted = 0
                    } else {
                        MyApp.typesList[position].wanted =
                            holder.view.row_meal_type_wanted2.text.toString().toInt()
                    }
                }

                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }

            })
        } else {
            constraintDuplicates.visibility = View.GONE
            constraintNoDuplicates.visibility = View.VISIBLE
            holder.view.row_meal_type_name.text = MyApp.typesList[position].name
            holder.view.row_meal_type_max.text = MyApp.typesList[position].max.toString()
            holder.view.row_meal_type_wanted.setText(MyApp.typesList[position].wanted.toString())
            holder.view.row_meal_type_max.text = MyApp.typesList[position].max.toString()
            holder.view.row_meal_type_wanted.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(p0: Editable?) {
                    if (holder.view.row_meal_type_wanted.text.toString() == "") {
                        MyApp.typesList[position].wanted = 0
                    } else {
                        MyApp.typesList[position].wanted =
                            holder.view.row_meal_type_wanted.text.toString().toInt()
                    }
                }

                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }

            })
        }

        holder.view.row_meal_type_delete.setOnClickListener {
            MyApp.typesList.removeAt(position)
            notifyItemRemoved(position)
            notifyItemRangeChanged(position, MyApp.typesList.size)
        }

        holder.view.row_meal_type_delete2.setOnClickListener {
            MyApp.typesList.removeAt(position)
            notifyItemRemoved(position)
            notifyItemRangeChanged(position, MyApp.typesList.size)
        }
    }

}


class GenerateMealTypesViewHolder(val view: View) : RecyclerView.ViewHolder(view)