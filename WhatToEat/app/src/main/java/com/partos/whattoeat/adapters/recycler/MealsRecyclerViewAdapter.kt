package com.partos.whattoeat.adapters.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.partos.whattoeat.R
import com.partos.whattoeat.activities.MainActivity
import com.partos.whattoeat.db.DataBaseHelper
import com.partos.whattoeat.fragments.meal.MealShowFragment
import com.partos.whattoeat.models.Meal
import kotlinx.android.synthetic.main.row_meal_extended.view.*

class MealsRecyclerViewAdapter(var mealsList: ArrayList<Meal>) :
    RecyclerView.Adapter<MealsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val cell = inflater.inflate(R.layout.row_meal_extended, parent, false)
        return MealsViewHolder(cell)
    }

    override fun getItemCount(): Int {
        return mealsList.size
    }

    override fun onBindViewHolder(holder: MealsViewHolder, position: Int) {
        holder.view.row_meal_extended_constraint_normal.visibility = View.VISIBLE
        holder.view.row_meal_extended_constraint_delete.visibility = View.GONE
        holder.view.row_meal_extended_name.text = mealsList[position].name
        holder.view.row_meal_extended_card.setOnClickListener {
            val fragment = MealShowFragment.newInstance(mealsList[position].id)
            (holder.view.context as MainActivity).supportFragmentManager
                .beginTransaction()
                .setCustomAnimations(
                    R.anim.enter_right_to_left, R.anim.exit_left_to_right,
                    R.anim.enter_left_to_right, R.anim.exit_right_to_left
                )
                .replace(R.id.main_frame_layout, fragment)
                .addToBackStack(MealShowFragment.toString())
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
            val ingredients = db.getIngredientList(mealsList[position].id)
            for (ingredient in ingredients) {
                db.deleteIngredient(ingredient.id)
            }
            db.deleteMeal(mealsList[position].id)
            mealsList.removeAt(position)
            notifyItemRemoved(position)
            notifyItemRangeChanged(position, mealsList.size)
        }
        holder.view.row_meal_extended_no.setOnClickListener {
            holder.view.row_meal_extended_constraint_normal.visibility = View.VISIBLE
            holder.view.row_meal_extended_constraint_delete.visibility = View.GONE
        }
    }

}

class MealsViewHolder(val view: View) : RecyclerView.ViewHolder(view)