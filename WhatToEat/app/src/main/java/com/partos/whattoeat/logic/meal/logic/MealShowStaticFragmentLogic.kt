package com.partos.whattoeat.logic.meal.logic

import android.content.Context
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.partos.whattoeat.R
import com.partos.whattoeat.adapters.MarginItemDecoration
import com.partos.whattoeat.adapters.recycler.IngredientsStaticRecyclerViewAdapter
import com.partos.whattoeat.db.DataBaseHelper

class MealShowStaticFragmentLogic {

    private lateinit var recyclerView: RecyclerView
    private lateinit var nameTextView: TextView
    private lateinit var db: DataBaseHelper

    fun initFragment(rootView: View, mealId: Long) {
        attachViews(rootView)
        db = DataBaseHelper(rootView.context)
        nameTextView.text = db.getMeal(mealId)[0].name
        attachRecyclerView(rootView.context, mealId)
    }

    private fun attachRecyclerView(context: Context, mealId: Long) {
        val mLayoutManager = LinearLayoutManager(context)
        recyclerView.layoutManager = mLayoutManager
        recyclerView.addItemDecoration(MarginItemDecoration(12))
        recyclerView.adapter = IngredientsStaticRecyclerViewAdapter(db.getIngredientList(mealId))
    }

    private fun attachViews(rootView: View) {
        recyclerView = rootView.findViewById(R.id.meal_show_static_recycler_view)
        nameTextView = rootView.findViewById(R.id.meal_show_static_name)
    }
}