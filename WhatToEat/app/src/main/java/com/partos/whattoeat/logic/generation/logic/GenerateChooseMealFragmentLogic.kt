package com.partos.whattoeat.logic.generation.logic

import android.content.Context
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.partos.whattoeat.R
import com.partos.whattoeat.adapters.MarginItemDecoration
import com.partos.whattoeat.adapters.recycler.GenerateChooseMealRecyclerViewAdapter
import com.partos.whattoeat.db.DataBaseHelper

class GenerateChooseMealFragmentLogic {

    private lateinit var recyclerView: RecyclerView
    private lateinit var noText1: TextView
    private lateinit var noText2: TextView

    fun initFragment(rootView: View, mealTypeId: Long) {
        attachViews(rootView)
        checkDatabase(rootView.context, mealTypeId)
    }

    private fun checkDatabase(context: Context, mealTypeId: Long) {
        val db = DataBaseHelper(context)
        val meals = db.getMealList(mealTypeId)
        if (meals.size == 0) {
            noText1.visibility = View.VISIBLE
            noText2.visibility = View.VISIBLE
            recyclerView.visibility = View.GONE
        } else {
            noText1.visibility = View.GONE
            noText2.visibility = View.GONE
            recyclerView.visibility = View.VISIBLE
            attachRecyclerView(context, mealTypeId)
        }
    }

    private fun attachRecyclerView(context: Context, mealTypeId: Long) {
        val db = DataBaseHelper(context)
        val mLayoutManager = LinearLayoutManager(context)
        recyclerView.layoutManager = mLayoutManager
        recyclerView.addItemDecoration(MarginItemDecoration(12))
        recyclerView.adapter = GenerateChooseMealRecyclerViewAdapter(db.getMealList(mealTypeId))

    }

    private fun attachViews(rootView: View) {
        recyclerView = rootView.findViewById(R.id.generate_choose_meal_recycler_view)
        noText1 = rootView.findViewById(R.id.generate_choose_meal_none_1)
        noText2 = rootView.findViewById(R.id.generate_choose_meal_none_2)
    }
}