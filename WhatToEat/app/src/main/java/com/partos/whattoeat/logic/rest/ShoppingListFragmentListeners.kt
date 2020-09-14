package com.partos.whattoeat.logic.rest

import android.content.Context
import android.view.View
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.partos.whattoeat.R
import com.partos.whattoeat.adapters.recycler.ToDoRecyclerViewAdapter
import com.partos.whattoeat.db.DataBaseHelper
import com.partos.whattoeat.models.ToDo

class ShoppingListFragmentListeners {

    private lateinit var recyclerView: RecyclerView
    private lateinit var deleteButton: Button

    fun initListeners(rootView: View) {
        attachViews(rootView)
        attachListeners(rootView.context)
    }

    private fun attachListeners(context: Context) {
        deleteButton.setOnClickListener {
            val db = DataBaseHelper(context)
            val toDoList = db.getToDoList()
            for (item in toDoList) {
                if (item.isDone == 1) {
                    db.deleteToDo(item.id)
                }
            }
            val newList = db.getToDoList()
            newList.add(ToDo(-1, "no", 0))
            recyclerView.adapter = ToDoRecyclerViewAdapter(newList)
        }
    }

    private fun attachViews(rootView: View) {
        recyclerView = rootView.findViewById(R.id.shopping_list_recycler_view)
        deleteButton = rootView.findViewById(R.id.shopping_list_button_delete)
    }
}