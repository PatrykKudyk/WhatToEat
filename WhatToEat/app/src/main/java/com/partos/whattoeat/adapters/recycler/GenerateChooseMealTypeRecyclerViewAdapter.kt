package com.partos.whattoeat.adapters.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.partos.whattoeat.R
import com.partos.whattoeat.activities.MainActivity
import com.partos.whattoeat.fragments.generation.GenerateChooseMealFragment
import com.partos.whattoeat.fragments.generation.GenerateMealsFromPackFragment
import com.partos.whattoeat.models.MealType
import kotlinx.android.synthetic.main.row_meal.view.*

class GenerateChooseMealTypeRecyclerViewAdapter(var mealTypesList: ArrayList<MealType>) :
    RecyclerView.Adapter<GenerateChooseMealTypeViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): GenerateChooseMealTypeViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val cell = inflater.inflate(R.layout.row_meal, parent, false)
        return GenerateChooseMealTypeViewHolder(cell)
    }

    override fun onBindViewHolder(holder: GenerateChooseMealTypeViewHolder, position: Int) {
        holder.view.row_meal_name.text = mealTypesList[position].name
        holder.view.row_meal_card.setOnClickListener {
            val fragment = GenerateChooseMealFragment.newInstance(
                mealTypesList[position].id)
            (holder.view.context as MainActivity).supportFragmentManager
                .beginTransaction()
                .setCustomAnimations(
                    R.anim.enter_right_to_left, R.anim.exit_left_to_right,
                    R.anim.enter_left_to_right, R.anim.exit_right_to_left
                )
                .replace(R.id.main_frame_layout, fragment)
                .addToBackStack(GenerateChooseMealFragment.toString())
                .commit()
        }
    }

    override fun getItemCount(): Int {
        return mealTypesList.size
    }

}

class GenerateChooseMealTypeViewHolder(val view: View) : RecyclerView.ViewHolder(view)