package com.partos.whattoeat.logic.meal.listeners

import android.view.View
import androidx.cardview.widget.CardView
import androidx.fragment.app.FragmentManager
import com.partos.whattoeat.R
import com.partos.whattoeat.fragments.meal.AddMealTypeFragment

class MealTypeChoiceFragmentListeners {

    private lateinit var addMealTypeButton: CardView

    fun initListeners(rootView: View, fragmentManager: FragmentManager) {
        attachViews(rootView)
        attachListeners(fragmentManager)
    }

    private fun attachListeners(fragmentManager: FragmentManager) {
        addMealTypeButton.setOnClickListener {
            val fragment = AddMealTypeFragment.newInstance()
            fragmentManager
                .beginTransaction()
                .setCustomAnimations(
                    R.anim.enter_right_to_left, R.anim.exit_left_to_right,
                    R.anim.enter_left_to_right, R.anim.exit_right_to_left
                )
                .replace(R.id.main_frame_layout, fragment)
                .addToBackStack(AddMealTypeFragment.toString())
                .commit()
        }
    }

    private fun attachViews(rootView: View) {
        addMealTypeButton = rootView.findViewById(R.id.meals_type_choice_add)
    }
}