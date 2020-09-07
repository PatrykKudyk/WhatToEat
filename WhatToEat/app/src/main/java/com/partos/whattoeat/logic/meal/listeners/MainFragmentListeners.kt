package com.partos.whattoeat.logic.meal.listeners

import android.view.View
import android.widget.Button
import androidx.fragment.app.FragmentManager
import com.partos.whattoeat.R
import com.partos.whattoeat.fragments.generation.GenerateMealsFragment
import com.partos.whattoeat.fragments.meal.MealsFragment

class MainFragmentListeners {

    private lateinit var mealsButton: Button
    private lateinit var generateButton: Button

    fun initListeners(rootView: View, fragmentManager: FragmentManager) {
        attachViews(rootView)
        attachListeners(fragmentManager)
    }

    private fun attachListeners(fragmentManager: FragmentManager) {
        mealsButton.setOnClickListener {
            val fragment = MealsFragment.newInstance()
            fragmentManager
                .beginTransaction()
                .setCustomAnimations(
                    R.anim.enter_right_to_left, R.anim.exit_left_to_right,
                    R.anim.enter_left_to_right, R.anim.exit_right_to_left
                )
                .replace(R.id.main_frame_layout, fragment)
                .addToBackStack(MealsFragment.toString())
                .commit()
        }

        generateButton.setOnClickListener {
            val fragment = GenerateMealsFragment.newInstance()
            fragmentManager
                .beginTransaction()
                .setCustomAnimations(
                    R.anim.enter_right_to_left, R.anim.exit_left_to_right,
                    R.anim.enter_left_to_right, R.anim.exit_right_to_left
                )
                .replace(R.id.main_frame_layout, fragment)
                .addToBackStack(GenerateMealsFragment.toString())
                .commit()
        }
    }

    private fun attachViews(rootView: View) {
        mealsButton = rootView.findViewById(R.id.main_button_meals)
        generateButton = rootView.findViewById(R.id.main_button_generate)
    }
}