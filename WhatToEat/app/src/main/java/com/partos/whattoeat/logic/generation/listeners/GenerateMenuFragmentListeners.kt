package com.partos.whattoeat.logic.generation.listeners

import android.view.View
import android.widget.Button
import androidx.fragment.app.FragmentManager
import com.partos.whattoeat.MyApp
import com.partos.whattoeat.R
import com.partos.whattoeat.fragments.generation.GenerateMealsFragment
import com.partos.whattoeat.fragments.generation.GenerateSavedListFragment

class GenerateMenuFragmentListeners {

    private lateinit var generateRandomButton: Button
    private lateinit var generateCustomButton: Button
    private lateinit var savedButton: Button

    fun initListeners(rootView: View, fragmentManager: FragmentManager) {
        attachViews(rootView)
        attachListeners(fragmentManager)
    }

    private fun attachListeners(fragmentManager: FragmentManager) {
        generateRandomButton.setOnClickListener {
            MyApp.allowDuplicates = false
            MyApp.typesList.clear()
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

        generateCustomButton.setOnClickListener {
            
        }

        savedButton.setOnClickListener {
            val fragment = GenerateSavedListFragment.newInstance()
            fragmentManager
                .beginTransaction()
                .setCustomAnimations(
                    R.anim.enter_right_to_left, R.anim.exit_left_to_right,
                    R.anim.enter_left_to_right, R.anim.exit_right_to_left
                )
                .replace(R.id.main_frame_layout, fragment)
                .addToBackStack(GenerateSavedListFragment.toString())
                .commit()
        }
    }

    private fun attachViews(rootView: View) {
        generateRandomButton = rootView.findViewById(R.id.generate_menu_generate_random)
        generateCustomButton = rootView.findViewById(R.id.generate_menu_generate_custom)
        savedButton = rootView.findViewById(R.id.generate_menu_saved)
    }
}