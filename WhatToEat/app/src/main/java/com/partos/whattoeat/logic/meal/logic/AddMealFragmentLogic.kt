package com.partos.whattoeat.logic.meal.logic

import android.content.Context
import android.view.View
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.partos.whattoeat.MyApp
import com.partos.whattoeat.R
import com.partos.whattoeat.adapters.MarginItemDecoration
import com.partos.whattoeat.adapters.recycler.IngredientsRecyclerViewAdapter
import com.partos.whattoeat.logic.meal.listeners.AddMealFragmentListeners

class AddMealFragmentLogic {

    private lateinit var recyclerView: RecyclerView

    fun initFragment(rootView: View, fragmentManager: FragmentManager) {

        attachViews(rootView)
        attachRecycler(rootView.context)
        AddMealFragmentListeners().initListeners(rootView, fragmentManager)
    }

    private fun attachRecycler(context: Context) {
        val mLayoutManager = LinearLayoutManager(context)
        recyclerView.layoutManager = mLayoutManager
        recyclerView.addItemDecoration(MarginItemDecoration(12))
        recyclerView.adapter = IngredientsRecyclerViewAdapter(MyApp.ingredientsList)
    }

    private fun attachViews(rootView: View) {
        recyclerView = rootView.findViewById(R.id.add_meal_recycler)
    }
}