package com.partos.whattoeat.logic.generation.logic

import android.content.Context
import android.view.View
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.partos.whattoeat.R
import com.partos.whattoeat.adapters.MarginItemDecoration
import com.partos.whattoeat.adapters.recycler.GenerateMealTypesRecyclerViewAdapter
import com.partos.whattoeat.logic.generation.listeners.GenerateMealsFragmentListeners

class GenerateMealsFragmentLogic {

    private lateinit var recyclerView: RecyclerView

    fun initFragment(rootView: View, fragmentManager: FragmentManager) {
        attachViews(rootView)
        attachRecyclerView(rootView.context)
        GenerateMealsFragmentListeners().initListeners(rootView, fragmentManager)
    }

    private fun attachRecyclerView(context: Context) {
        val mLayoutManager = LinearLayoutManager(context)
        recyclerView.layoutManager = mLayoutManager
        recyclerView.addItemDecoration(MarginItemDecoration(12))
        recyclerView.adapter = GenerateMealTypesRecyclerViewAdapter(false)
    }

    private fun attachViews(rootView: View) {
        recyclerView = rootView.findViewById(R.id.generate_meals_recycler_view)
    }
}