package com.partos.whattoeat.logic.generation.logic

import android.content.Context
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.partos.whattoeat.R
import com.partos.whattoeat.adapters.MarginItemDecoration
import com.partos.whattoeat.adapters.recycler.GenerateChooseMealTypeRecyclerViewAdapter
import com.partos.whattoeat.db.DataBaseHelper

class GenerateChooseMealTypeFragmentLogic {

    private lateinit var recyclerView: RecyclerView
    private lateinit var noText1: TextView
    private lateinit var noText2: TextView

    fun initFragment(rootView: View) {
        attachViews(rootView)
        checkDatabase(rootView.context)
    }

    private fun checkDatabase(context: Context) {
        val db = DataBaseHelper(context)
        val mealTypes = db.getMealTypeList()
        if (mealTypes.size == 0) {
            noText1.visibility = View.VISIBLE
            noText2.visibility = View.VISIBLE
            recyclerView.visibility = View.GONE
        } else {
            noText1.visibility = View.GONE
            noText2.visibility = View.GONE
            recyclerView.visibility = View.VISIBLE
            attachRecyclerView(context)
        }
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
        noText1 = rootView.findViewById(R.id.generate_choose_meal_type_none_1)
        noText2 = rootView.findViewById(R.id.generate_choose_meal_type_none_2)
    }
}