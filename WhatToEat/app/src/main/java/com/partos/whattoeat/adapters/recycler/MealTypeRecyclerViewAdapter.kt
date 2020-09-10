package com.partos.whattoeat.adapters.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.partos.whattoeat.R
import com.partos.whattoeat.activities.MainActivity
import com.partos.whattoeat.fragments.meal.AllMealsCategoryFragment
import com.partos.whattoeat.models.MealType
import kotlinx.android.synthetic.main.row_meal.view.*

class MealTypeRecyclerViewAdapter(val mealTypesList: ArrayList<MealType>) :
    RecyclerView.Adapter<MealTypeViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealTypeViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val cell = inflater.inflate(R.layout.row_meal, parent, false)
        return MealTypeViewHolder(cell)
    }

    override fun getItemCount(): Int {
        return mealTypesList.size
    }

    override fun onBindViewHolder(holder: MealTypeViewHolder, position: Int) {
        holder.view.row_meal_name.text = mealTypesList[position].name
        holder.view.row_meal_card.setOnClickListener {
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
    }

}


class MealTypeViewHolder(val view: View) : RecyclerView.ViewHolder(view)