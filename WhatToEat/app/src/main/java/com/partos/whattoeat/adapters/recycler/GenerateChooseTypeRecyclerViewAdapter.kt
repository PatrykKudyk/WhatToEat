package com.partos.whattoeat.adapters.recycler

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.partos.whattoeat.MyApp
import com.partos.whattoeat.R
import com.partos.whattoeat.activities.MainActivity
import com.partos.whattoeat.db.DataBaseHelper
import com.partos.whattoeat.logic.ToastHelper
import com.partos.whattoeat.models.MealType
import com.partos.whattoeat.models.MealTypeGeneration
import kotlinx.android.synthetic.main.row_meal.view.*

class GenerateChooseTypeRecyclerViewAdapter(val mealTypes: ArrayList<MealType>) :
    RecyclerView.Adapter<GenerateChooseTypeViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): GenerateChooseTypeViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val cell = inflater.inflate(R.layout.row_meal, parent, false)
        return GenerateChooseTypeViewHolder(cell)
    }

    override fun getItemCount(): Int {
        return mealTypes.size
    }

    override fun onBindViewHolder(holder: GenerateChooseTypeViewHolder, position: Int) {
        holder.view.row_meal_name.text = mealTypes[position].name
        holder.view.row_meal_card.setOnClickListener {
            if(!isTypeChosen(holder.view.context, position)) {
                if (!isTypeEmpty(holder.view.context, position)) {
                    val db = DataBaseHelper(holder.view.context)
                    MyApp.typesList.add(
                        MealTypeGeneration(
                            mealTypes[position].id,
                            mealTypes[position].name,
                            0,
                            db.getMealList(mealTypes[position].id).size
                        )
                    )
                    (holder.view.context as MainActivity)
                        .supportFragmentManager
                        .popBackStack()
                } else {
                    ToastHelper().mealTypeEmpty(holder.view.context)
                }
            } else {
                ToastHelper().mealTypeAlreadyChosen(holder.view.context)
            }
        }
    }

    private fun isTypeEmpty(context: Context, position: Int): Boolean {
        val db = DataBaseHelper(context)
        val meals = db.getMealList(mealTypes[position].id)
        return meals.size == 0
    }

    private fun isTypeChosen(context: Context, position: Int): Boolean {
        for (type in MyApp.typesList) {
            if (type.id == mealTypes[position].id) {
                return true
            }
        }
        return false
    }

}

class GenerateChooseTypeViewHolder(val view: View) : RecyclerView.ViewHolder(view)