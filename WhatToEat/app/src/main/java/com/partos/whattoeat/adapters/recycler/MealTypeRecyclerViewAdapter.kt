package com.partos.whattoeat.adapters.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.partos.whattoeat.R
import com.partos.whattoeat.activities.MainActivity
import com.partos.whattoeat.db.DataBaseHelper
import com.partos.whattoeat.fragments.meal.AllMealsCategoryFragment
import com.partos.whattoeat.models.MealType
import kotlinx.android.synthetic.main.row_meal_extended.view.*

class MealTypeRecyclerViewAdapter(val mealTypesList: ArrayList<MealType>) :
    RecyclerView.Adapter<MealTypeViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealTypeViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val cell = inflater.inflate(R.layout.row_meal_extended, parent, false)
        return MealTypeViewHolder(cell)
    }

    override fun getItemCount(): Int {
        return mealTypesList.size
    }

    override fun onBindViewHolder(holder: MealTypeViewHolder, position: Int) {
        holder.view.row_meal_extended_name.text = mealTypesList[position].name
        holder.view.row_meal_extended_card.setOnClickListener {
            val fragment = AllMealsCategoryFragment.newInstance(mealTypesList[position].id)
            (holder.view.context as MainActivity).supportFragmentManager
                .beginTransaction()
                .setCustomAnimations(
                    R.anim.enter_right_to_left, R.anim.exit_left_to_right,
                    R.anim.enter_left_to_right, R.anim.exit_right_to_left
                )
                .replace(R.id.main_frame_layout, fragment)
                .addToBackStack(AllMealsCategoryFragment.toString())
                .commit()
        }

        holder.view.row_meal_extended_delete.setOnClickListener {
            holder.view.row_meal_extended_constraint_normal.visibility = View.GONE
            holder.view.row_meal_extended_constraint_delete.visibility = View.VISIBLE
        }
        holder.view.row_meal_extended_yes.setOnClickListener {
            holder.view.row_meal_extended_constraint_normal.visibility = View.VISIBLE
            holder.view.row_meal_extended_constraint_delete.visibility = View.GONE
            val db = DataBaseHelper(holder.view.context)
            val meals = db.getMealList(mealTypesList[position].id)
            for (meal in meals) {
                val ingredients = db.getIngredientList(meal.id)
                for (ingredient in ingredients) {
                    db.deleteIngredient(ingredient.id)
                }
                db.deleteMeal(meal.id)
            }
            db.deleteMealType(mealTypesList[position].id)
            mealTypesList.removeAt(position)
            notifyItemRemoved(position)
            notifyItemRangeChanged(position, mealTypesList.size)
        }
        holder.view.row_meal_extended_no.setOnClickListener {
            holder.view.row_meal_extended_constraint_normal.visibility = View.VISIBLE
            holder.view.row_meal_extended_constraint_delete.visibility = View.GONE
        }
    }

}


class MealTypeViewHolder(val view: View) : RecyclerView.ViewHolder(view)