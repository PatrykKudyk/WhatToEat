package com.partos.whattoeat.logic.logic

import android.content.Context
import android.view.View
import android.widget.TextView
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.partos.whattoeat.R
import com.partos.whattoeat.adapters.MarginItemDecoration
import com.partos.whattoeat.adapters.recycler.IngredientsEditRecyclerViewAdapter
import com.partos.whattoeat.adapters.recycler.MealsRecyclerViewAdapter
import com.partos.whattoeat.db.DataBaseHelper
import com.partos.whattoeat.models.Ingredient
import com.partos.whattoeat.models.Meal

class MealShowFragmentLogic {

    private lateinit var recyclerView: RecyclerView
    private lateinit var nameTextView: TextView
    private lateinit var db: DataBaseHelper
    private lateinit var meal: Meal
    private lateinit var ingredientList: ArrayList<Ingredient>

    fun initFragment(rootView: View, mealId: Long) {
        attachViews(rootView)
        attachVariables(rootView.context, mealId)
        attachRecyclerView(rootView.context)
        nameTextView.text = meal.name
    }

    private fun attachVariables(context: Context, mealId: Long) {
        db = DataBaseHelper(context)
        meal = db.getMealList(mealId)[0]
        ingredientList = db.getIngredientList(mealId)
    }

    private fun attachRecyclerView(context: Context) {
        val mLayoutManager = LinearLayoutManager(context)
        recyclerView.layoutManager = mLayoutManager
        recyclerView.addItemDecoration(MarginItemDecoration(12))
        recyclerView.adapter = IngredientsEditRecyclerViewAdapter(ingredientList)
    }

    private fun attachViews(rootView: View) {
        nameTextView = rootView.findViewById(R.id.show_meal_name)
        recyclerView = rootView.findViewById(R.id.show_meal_recycler)
    }
}