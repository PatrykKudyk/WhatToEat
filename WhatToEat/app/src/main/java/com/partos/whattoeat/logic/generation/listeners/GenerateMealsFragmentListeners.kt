package com.partos.whattoeat.logic.generation.listeners

import android.content.Context
import android.view.View
import android.widget.Button
import androidx.cardview.widget.CardView
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.partos.whattoeat.R
import com.partos.whattoeat.adapters.recycler.GenerateMealTypesRecyclerViewAdapter

class GenerateMealsFragmentListeners {

    private lateinit var buttonYes: Button
    private lateinit var buttonNo: Button
    private lateinit var recyclerView: RecyclerView
    private lateinit var addNewButton: CardView
    private var yes = false

    fun initListeners(rootView: View, fragmentManager: FragmentManager) {
        attachViews(rootView)
        attachListeners(fragmentManager, rootView.context)
    }

    private fun attachListeners(fragmentManager: FragmentManager, context: Context) {
        buttonYes.setOnClickListener {
            if (!yes) {
                yes = true
                buttonYes.background = context.getDrawable(R.drawable.button_background_red)
                buttonNo.background = context.getDrawable(R.drawable.button_background_red_light)
                recyclerView.adapter = GenerateMealTypesRecyclerViewAdapter(true)
            }
        }
        buttonNo.setOnClickListener {
            if (yes) {
                yes = false
                buttonNo.background = context.getDrawable(R.drawable.button_background_red)
                buttonYes.background = context.getDrawable(R.drawable.button_background_red_light)
                recyclerView.adapter = GenerateMealTypesRecyclerViewAdapter(false)
            }
        }
        addNewButton.setOnClickListener {

        }
    }

    private fun attachViews(rootView: View) {
        buttonYes = rootView.findViewById(R.id.generate_meals_button_yes)
        buttonNo = rootView.findViewById(R.id.generate_meals_button_no)
        recyclerView = rootView.findViewById(R.id.generate_meals_recycler_view)
        addNewButton = rootView.findViewById(R.id.generate_meals_add)
    }
}