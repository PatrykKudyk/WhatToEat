package com.partos.whattoeat.logic.generation.logic

import android.content.Context
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.partos.whattoeat.R
import com.partos.whattoeat.adapters.MarginItemDecoration
import com.partos.whattoeat.db.DataBaseHelper

class GenerateSavedListFragmentLogic {

    private lateinit var recyclerView: RecyclerView
    private lateinit var noneSavedTextView: TextView
    private lateinit var db: DataBaseHelper

    fun initFragment(rootView: View) {
        attachViews(rootView)
        db = DataBaseHelper(rootView.context)
        if (isSomethingSaved()) {
            attachRecyclerView(rootView.context)
        } else {
            noneSavedTextView.visibility = View.VISIBLE
            recyclerView.visibility = View.GONE
        }
    }

    private fun attachRecyclerView(context: Context) {
        val mLayoutManager = LinearLayoutManager(context)
        recyclerView.layoutManager = mLayoutManager
        recyclerView.addItemDecoration(MarginItemDecoration(12))
        val db = DataBaseHelper(context)
        recyclerView.adapter
    }

    private fun isSomethingSaved(): Boolean {
        return false
    }

    private fun attachViews(rootView: View) {
        recyclerView = rootView.findViewById(R.id.generate_saved_recycler_view)
        noneSavedTextView = rootView.findViewById(R.id.generate_saved_text_nothing)
    }

}