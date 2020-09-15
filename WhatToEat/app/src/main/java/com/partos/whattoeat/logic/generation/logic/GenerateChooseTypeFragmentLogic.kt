package com.partos.whattoeat.logic.generation.logic

import android.content.Context
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.partos.whattoeat.R
import com.partos.whattoeat.adapters.MarginItemDecoration
import com.partos.whattoeat.adapters.recycler.GenerateChooseTypeRecyclerViewAdapter
import com.partos.whattoeat.db.DataBaseHelper

class GenerateChooseTypeFragmentLogic {

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
            noText2.visibility = View.VISIBLE
            noText1.visibility = View.VISIBLE
            recyclerView.visibility = View.GONE
        } else {
            noText2.visibility = View.GONE
            noText1.visibility = View.GONE
            recyclerView.visibility = View.VISIBLE
            attachRecyclerView(context)
        }
    }

    private fun attachRecyclerView(context: Context) {
        val db = DataBaseHelper(context)
        val mLayoutManager = LinearLayoutManager(context)
        recyclerView.layoutManager = mLayoutManager
        recyclerView.addItemDecoration(MarginItemDecoration(12))
        recyclerView.adapter = GenerateChooseTypeRecyclerViewAdapter(db.getMealTypeList())
    }

    private fun attachViews(rootView: View) {
        recyclerView = rootView.findViewById(R.id.generate_choose_recycler_view)
        noText1 = rootView.findViewById(R.id.generate_choose_none_1)
        noText2 = rootView.findViewById(R.id.generate_choose_none_2)
    }
}