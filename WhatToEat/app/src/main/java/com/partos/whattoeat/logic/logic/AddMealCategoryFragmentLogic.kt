package com.partos.whattoeat.logic.logic

import android.content.Context
import android.view.View
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.partos.whattoeat.R
import com.partos.whattoeat.adapters.MarginItemDecoration
import com.partos.whattoeat.adapters.recycler.IngredientsRecyclerViewAdapter
import com.partos.whattoeat.logic.listeners.AddMealCategoryFragmentListeners

class AddMealCategoryFragmentLogic {

    private lateinit var recyclerView: RecyclerView

    fun initFragment(rootView: View, fragmentManager: FragmentManager, typeId: Long) {

        attachViews(rootView)
        attachRecycler(rootView.context)
        AddMealCategoryFragmentListeners().initListeners(rootView, fragmentManager, typeId)
    }

    private fun attachRecycler(context: Context) {
        val mLayoutManager = LinearLayoutManager(context)
        recyclerView.layoutManager = mLayoutManager
        recyclerView.addItemDecoration(MarginItemDecoration(12))
        recyclerView.adapter = IngredientsRecyclerViewAdapter()
    }

    private fun attachViews(rootView: View) {
        recyclerView = rootView.findViewById(R.id.add_meal_category_recycler)
    }
}