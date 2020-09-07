package com.partos.whattoeat.logic.listeners

import android.view.View
import androidx.cardview.widget.CardView
import androidx.fragment.app.FragmentManager
import com.partos.whattoeat.R
import com.partos.whattoeat.fragments.meal.AddMealTypeFragment

class MealCategoriesFragmentListeners {

    private lateinit var addButton: CardView

    fun initListeners(rootView: View, fragmentManager: FragmentManager) {
        attachViews(rootView)
        attachListeners(fragmentManager)
    }

    private fun attachListeners(fragmentManager: FragmentManager) {
        addButton.setOnClickListener {
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
        addButton = rootView.findViewById(R.id.meal_categories_add)
    }
}