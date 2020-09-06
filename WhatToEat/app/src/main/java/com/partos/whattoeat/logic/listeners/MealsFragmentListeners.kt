package com.partos.whattoeat.logic.listeners

import android.view.View
import android.widget.Button
import androidx.fragment.app.FragmentManager
import com.partos.whattoeat.R
import com.partos.whattoeat.fragments.AllMealsFragment
import com.partos.whattoeat.fragments.MealCategoriesFragment
import com.partos.whattoeat.fragments.MealsFragment

class MealsFragmentListeners {

    private lateinit var categoryButton: Button
    private lateinit var allButton: Button

    fun initListeners(rootView: View, fragmentManager: FragmentManager) {
        attachViews(rootView)
        attachListeners(fragmentManager)
    }

    private fun attachListeners(fragmentManager: FragmentManager) {
        categoryButton.setOnClickListener {
            val fragment = MealCategoriesFragment.newInstance()
            fragmentManager
                .beginTransaction()
                .setCustomAnimations(
                    R.anim.enter_right_to_left, R.anim.exit_left_to_right,
                    R.anim.enter_left_to_right, R.anim.exit_right_to_left
                )
                .replace(R.id.main_frame_layout, fragment)
                .addToBackStack(MealCategoriesFragment.toString())
                .commit()
        }

        allButton.setOnClickListener {
            val fragment = AllMealsFragment.newInstance()
            fragmentManager
                .beginTransaction()
                .setCustomAnimations(
                    R.anim.enter_right_to_left, R.anim.exit_left_to_right,
                    R.anim.enter_left_to_right, R.anim.exit_right_to_left
                )
                .replace(R.id.main_frame_layout, fragment)
                .addToBackStack(AllMealsFragment.toString())
                .commit()
        }
    }

    private fun attachViews(rootView: View) {
        categoryButton = rootView.findViewById(R.id.meals_button_category)
        allButton = rootView.findViewById(R.id.meals_button_all)
    }
}