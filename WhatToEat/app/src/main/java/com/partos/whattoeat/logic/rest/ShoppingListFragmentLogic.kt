package com.partos.whattoeat.logic.rest

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.partos.whattoeat.R
import com.partos.whattoeat.adapters.MarginItemDecoration
import com.partos.whattoeat.adapters.recycler.ToDoRecyclerViewAdapter
import com.partos.whattoeat.db.DataBaseHelper
import com.partos.whattoeat.models.ToDo

class ShoppingListFragmentLogic {

    private lateinit var recyclerView: RecyclerView

    fun initFragment(rootView: View) {
        attachViews(rootView)
        attachRecyclerView(rootView.context)
        ShoppingListFragmentListeners().initListeners(rootView)
    }

    private fun attachRecyclerView(context: Context) {
        val mLayoutManager = LinearLayoutManager(context)
        val db = DataBaseHelper(context)
        recyclerView.layoutManager = mLayoutManager
        recyclerView.addItemDecoration(MarginItemDecoration(12))
        val toDoList = db.getToDoList()
        toDoList.add(
            ToDo(0, "no", 0)
        )
        recyclerView.adapter = ToDoRecyclerViewAdapter(toDoList)
    }

    private fun attachViews(rootView: View) {
        recyclerView = rootView.findViewById(R.id.shopping_list_recycler_view)
    }
}