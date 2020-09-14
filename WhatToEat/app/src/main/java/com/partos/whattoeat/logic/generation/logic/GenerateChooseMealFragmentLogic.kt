package com.partos.whattoeat.logic.generation.logic

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.partos.whattoeat.R
import com.partos.whattoeat.adapters.MarginItemDecoration
import com.partos.whattoeat.adapters.recycler.GenerateChooseMealRecyclerViewAdapter
import com.partos.whattoeat.db.DataBaseHelper

class GenerateChooseMealFragmentLogic {

    private lateinit var recyclerView: RecyclerView

    fun initFragment(rootView: View, mealTypeId: Long) {
        attachViews(rootView)
        attachRecyclerView(rootView.context, mealTypeId)
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
    }
}