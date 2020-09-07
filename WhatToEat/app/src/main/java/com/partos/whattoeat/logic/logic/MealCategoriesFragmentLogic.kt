package com.partos.whattoeat.logic.logic

import android.content.Context
import android.view.View
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.partos.whattoeat.R
import com.partos.whattoeat.adapters.MarginItemDecoration
import com.partos.whattoeat.db.DataBaseHelper
import com.partos.whattoeat.logic.listeners.MealCategoriesFragmentListeners

class MealCategoriesFragmentLogic {

    private lateinit var recyclerView: RecyclerView

    fun initFragment(rootView: View, fragmentManager: FragmentManager) {
        attachViews(rootView)
        attachRecyclerView(rootView.context)
        MealCategoriesFragmentListeners().initListeners(rootView, fragmentManager)
    }

    private fun attachRecyclerView(context: Context) {
        val mLayoutManager = LinearLayoutManager(context)
        recyclerView.layoutManager = mLayoutManager
        recyclerView.addItemDecoration(MarginItemDecoration(12))
        val db = DataBaseHelper(context)
        recyclerView.adapter
    }

    private fun attachViews(rootView: View) {
        recyclerView = rootView.findViewById(R.id.meal_categories_recycler_view)
    }
}