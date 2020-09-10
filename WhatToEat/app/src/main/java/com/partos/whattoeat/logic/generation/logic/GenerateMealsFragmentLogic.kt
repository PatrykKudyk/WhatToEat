package com.partos.whattoeat.logic.generation.logic

import android.content.Context
import android.view.View
import android.widget.Button
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.partos.whattoeat.MyApp
import com.partos.whattoeat.R
import com.partos.whattoeat.adapters.MarginItemDecoration
import com.partos.whattoeat.adapters.recycler.GenerateMealTypesRecyclerViewAdapter
import com.partos.whattoeat.logic.generation.listeners.GenerateMealsFragmentListeners

class GenerateMealsFragmentLogic {

    private lateinit var recyclerView: RecyclerView
    private lateinit var yesButton: Button
    private lateinit var noButton: Button

    fun initFragment(rootView: View, fragmentManager: FragmentManager) {
        attachViews(rootView)
        attachRecyclerView(rootView.context)
        setYesNo(rootView.context)
        GenerateMealsFragmentListeners().initListeners(rootView, fragmentManager)
    }

    private fun setYesNo(context: Context) {
        if (MyApp.allowDuplicates) {
            yesButton.background = context.getDrawable(R.drawable.button_background_red)
            noButton.background = context.getDrawable(R.drawable.button_background_red_light)
        } else {
            noButton.background = context.getDrawable(R.drawable.button_background_red)
            yesButton.background = context.getDrawable(R.drawable.button_background_red_light)
        }
    }

    private fun attachRecyclerView(context: Context) {
        val mLayoutManager = LinearLayoutManager(context)
        recyclerView.layoutManager = mLayoutManager
        recyclerView.addItemDecoration(MarginItemDecoration(12))
        recyclerView.adapter = GenerateMealTypesRecyclerViewAdapter(MyApp.allowDuplicates)
    }

    private fun attachViews(rootView: View) {
        recyclerView = rootView.findViewById(R.id.generate_meals_recycler_view)
        yesButton = rootView.findViewById(R.id.generate_meals_button_yes)
        noButton = rootView.findViewById(R.id.generate_meals_button_no)
    }
}