package com.partos.whattoeat.logic.generation.logic

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.partos.whattoeat.R
import com.partos.whattoeat.adapters.MarginItemDecoration
import com.partos.whattoeat.adapters.recycler.GenerateChooseMealTypeRecyclerViewAdapter
import com.partos.whattoeat.db.DataBaseHelper

class GenerateChooseMealTypeFragmentLogic {

    private lateinit var recyclerView: RecyclerView

    fun initFragment(rootView: View) {
        attachViews(rootView)
        attachRecyclerView(rootView.context)
    }

    private fun attachRecyclerView(context: Context) {
        val db = DataBaseHelper(context)
        val mLayoutManager = LinearLayoutManager(context)
        recyclerView.layoutManager = mLayoutManager
        recyclerView.addItemDecoration(MarginItemDecoration(12))
        recyclerView.adapter = GenerateChooseMealTypeRecyclerViewAdapter(db.getMealTypeList())

    }

    private fun attachViews(rootView: View) {
        recyclerView = rootView.findViewById(R.id.generate_choose_meal_type_recycler_view)
    }
}