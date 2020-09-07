package com.partos.whattoeat.logic.meal.logic

import android.content.Context
import android.view.View
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.partos.whattoeat.R
import com.partos.whattoeat.adapters.MarginItemDecoration
import com.partos.whattoeat.adapters.recycler.MealTypeChoiceRecyclerViewAdapter
import com.partos.whattoeat.db.DataBaseHelper
import com.partos.whattoeat.logic.meal.listeners.MealTypeChoiceFragmentListeners

class MealTypeChoiceFragmentLogic {

    private lateinit var recyclerView: RecyclerView

    fun initFragment(rootView: View, fragmentManager: FragmentManager) {
        attachViews(rootView)
        attachRecyclerView(rootView.context)
        MealTypeChoiceFragmentListeners().initListeners(rootView, fragmentManager)
    }

    private fun attachRecyclerView(context: Context) {
        val db = DataBaseHelper(context)
        val mLayoutManager = LinearLayoutManager(context)
        recyclerView.layoutManager = mLayoutManager
        recyclerView.addItemDecoration(MarginItemDecoration(12))
        recyclerView.adapter = MealTypeChoiceRecyclerViewAdapter(db.getMealTypeList())
    }

    private fun attachViews(rootView: View) {
        recyclerView = rootView.findViewById(R.id.meals_type_choice_recycler)
    }
}