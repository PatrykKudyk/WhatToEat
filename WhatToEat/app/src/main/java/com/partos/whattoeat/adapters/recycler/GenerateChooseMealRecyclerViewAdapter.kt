package com.partos.whattoeat.adapters.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.partos.whattoeat.MyApp
import com.partos.whattoeat.R
import com.partos.whattoeat.activities.MainActivity
import com.partos.whattoeat.fragments.generation.GenerateChooseMealFragment
import com.partos.whattoeat.fragments.generation.GenerateChooseMealTypeFragment
import com.partos.whattoeat.fragments.meal.AddMealFragment
import com.partos.whattoeat.fragments.meal.MealTypeChoiceFragment
import com.partos.whattoeat.models.Meal
import kotlinx.android.synthetic.main.row_meal.view.*

class GenerateChooseMealRecyclerViewAdapter(var mealList: ArrayList<Meal>) :
    RecyclerView.Adapter<GenerateChooseMealViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): GenerateChooseMealViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val cell = inflater.inflate(R.layout.row_meal, parent, false)
        return GenerateChooseMealViewHolder(cell)
    }

    override fun onBindViewHolder(holder: GenerateChooseMealViewHolder, position: Int) {
        holder.view.row_meal_name.text = mealList[position].name
        holder.view.row_meal_card.setOnClickListener {
            MyApp.mealList.add(mealList[holder.adapterPosition])
            val activity = (holder.view.context) as MainActivity
            activity.supportFragmentManager
                .popBackStack(GenerateChooseMealTypeFragment.toString(), FragmentManager.POP_BACK_STACK_INCLUSIVE)
            activity.supportFragmentManager
                .popBackStack(GenerateChooseMealFragment.toString(), FragmentManager.POP_BACK_STACK_INCLUSIVE)

        }
    }

    override fun getItemCount(): Int {
        return mealList.size
    }

}

class GenerateChooseMealViewHolder(val view: View) : RecyclerView.ViewHolder(view)