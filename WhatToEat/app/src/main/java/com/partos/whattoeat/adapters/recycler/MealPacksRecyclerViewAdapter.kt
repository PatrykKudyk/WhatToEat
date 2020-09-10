package com.partos.whattoeat.adapters.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.partos.whattoeat.R
import com.partos.whattoeat.activities.MainActivity
import com.partos.whattoeat.fragments.generation.GenerateMealsFromPackFragment
import com.partos.whattoeat.models.MealPack
import kotlinx.android.synthetic.main.row_meal.view.*


class MealPacksRecyclerViewAdapter(var mealPacksList: ArrayList<MealPack>) :
    RecyclerView.Adapter<MealPacksViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealPacksViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val cell = inflater.inflate(R.layout.row_meal, parent, false)
        return MealPacksViewHolder(cell)
    }

    override fun getItemCount(): Int {
        return mealPacksList.size
    }

    override fun onBindViewHolder(holder: MealPacksViewHolder, position: Int) {
        holder.view.row_meal_name.text = mealPacksList[position].name
        holder.view.row_meal_card.setOnClickListener {
            val fragment = GenerateMealsFromPackFragment.newInstance(
                mealPacksList[position].id,
                mealPacksList[position].name
            )
            (holder.view.context as MainActivity).supportFragmentManager
                .beginTransaction()
                .setCustomAnimations(
                    R.anim.enter_right_to_left, R.anim.exit_left_to_right,
                    R.anim.enter_left_to_right, R.anim.exit_right_to_left
                )
                .replace(R.id.main_frame_layout, fragment)
                .addToBackStack(GenerateMealsFromPackFragment.toString())
                .commit()
        }
    }

}

class MealPacksViewHolder(val view: View) : RecyclerView.ViewHolder(view)