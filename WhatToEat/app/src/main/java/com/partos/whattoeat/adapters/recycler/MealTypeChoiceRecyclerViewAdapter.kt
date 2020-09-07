package com.partos.whattoeat.adapters.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.partos.whattoeat.MyApp
import com.partos.whattoeat.R
import com.partos.whattoeat.activities.MainActivity
import com.partos.whattoeat.db.DataBaseHelper
import com.partos.whattoeat.fragments.AddMealFragment
import com.partos.whattoeat.fragments.MealTypeChoiceFragment
import com.partos.whattoeat.models.Ingredient
import com.partos.whattoeat.models.MealType
import kotlinx.android.synthetic.main.row_meal.view.*

class MealTypeChoiceRecyclerViewAdapter(var mealTypeList: ArrayList<MealType>) :
    RecyclerView.Adapter<MealTypeChoiceViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealTypeChoiceViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val cell = inflater.inflate(R.layout.row_meal, parent, false)
        return MealTypeChoiceViewHolder(cell)
    }

    override fun getItemCount(): Int {
        return mealTypeList.size
    }

    override fun onBindViewHolder(holder: MealTypeChoiceViewHolder, position: Int) {
        holder.view.row_meal_name.text = mealTypeList[position].name
        holder.view.row_meal_card.setOnClickListener {
            val db = DataBaseHelper(holder.view.context)
            db.addMeal(MyApp.mealName, mealTypeList[position].id)
            val meal = db.getMealList(MyApp.mealName)[0]
            for (ingredient in MyApp.ingredientsList){
                db.addIngredient(Ingredient(
                    0,
                    ingredient.name,
                    ingredient.amount,
                    ingredient.type,
                    meal.id
                ))
            }

            val activity = (holder.view.context) as MainActivity
            activity.supportFragmentManager
                .popBackStack(AddMealFragment.toString(), FragmentManager.POP_BACK_STACK_INCLUSIVE)
            activity.supportFragmentManager
                .popBackStack(MealTypeChoiceFragment.toString(), FragmentManager.POP_BACK_STACK_INCLUSIVE)

        }
    }

}


class MealTypeChoiceViewHolder(val view: View) : RecyclerView.ViewHolder(view)