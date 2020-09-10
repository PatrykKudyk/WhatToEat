package com.partos.whattoeat.logic.generation.logic

import android.content.Context
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.partos.whattoeat.R
import com.partos.whattoeat.adapters.MarginItemDecoration
import com.partos.whattoeat.adapters.recycler.MealsStaticRecyclerViewAdapter
import com.partos.whattoeat.db.DataBaseHelper
import com.partos.whattoeat.logic.generation.listeners.GenerateMealsFromPackFragmentListeners
import com.partos.whattoeat.models.Meal

class GenerateMealsFromPackFragmentLogic {

    private lateinit var recyclerView: RecyclerView
    private lateinit var nameText: TextView

    fun initFragment(rootView: View, mealPackId: Long, mealPackName: String) {
        attachViews(rootView)
        nameText.text = mealPackName
        attachRecyclerView(rootView.context, mealPackId)
        GenerateMealsFromPackFragmentListeners().initListeners(rootView, mealPackId)
    }

    private fun attachRecyclerView(context: Context, mealPackId: Long) {
        val mLayoutManager = LinearLayoutManager(context)
        recyclerView.layoutManager = mLayoutManager
        recyclerView.addItemDecoration(MarginItemDecoration(12))
        recyclerView.adapter = MealsStaticRecyclerViewAdapter(generateMeals(context, mealPackId))
    }

    private fun generateMeals(context: Context, mealPackId: Long): ArrayList<Meal> {
        val db = DataBaseHelper(context)
        val mealList = ArrayList<Meal>()
        val meals = db.getMealsFromPackList(mealPackId)
        for (meal in meals) {
            val mealFinal = db.getMeal(meal.mealId)
            mealList.add(mealFinal[0])
        }
        return mealList
    }

    private fun attachViews(rootView: View) {
        recyclerView = rootView.findViewById(R.id.generate_meals_from_pack_recycler_view)
        nameText = rootView.findViewById(R.id.generate_meals_from_pack_text_name)
    }
}