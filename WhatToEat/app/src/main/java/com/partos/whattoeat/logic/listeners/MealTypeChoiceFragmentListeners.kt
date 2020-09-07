package com.partos.whattoeat.logic.listeners

import android.view.View
import androidx.cardview.widget.CardView
import androidx.fragment.app.FragmentManager
import com.partos.whattoeat.R

class MealTypeChoiceFragmentListeners {

    private lateinit var addMealTypeButton: CardView

    fun initListeners(rootView: View, fragmentManager: FragmentManager) {
        attachViews(rootView)
        attachListeners(fragmentManager)
    }

    private fun attachListeners(fragmentManager: FragmentManager) {
        addMealTypeButton.setOnClickListener {

        }
    }

    private fun attachViews(rootView: View) {
        addMealTypeButton = rootView.findViewById(R.id.meals_type_choice_add)
    }
}