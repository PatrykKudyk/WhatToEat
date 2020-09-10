package com.partos.whattoeat.adapters.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.partos.whattoeat.R
import com.partos.whattoeat.activities.MainActivity
import com.partos.whattoeat.fragments.meal.MealShowFragment
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
        holder.view.row_meal_card.setOnClickListener {
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
    }

}

class MealsViewHolder(val view: View) : RecyclerView.ViewHolder(view)