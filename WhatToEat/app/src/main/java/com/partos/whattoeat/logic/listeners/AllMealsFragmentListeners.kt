package com.partos.whattoeat.logic.listeners

import android.view.View
import androidx.cardview.widget.CardView
import androidx.fragment.app.FragmentManager
import com.partos.whattoeat.MyApp
import com.partos.whattoeat.R
import com.partos.whattoeat.fragments.AddMealFragment
import com.partos.whattoeat.fragments.MealsFragment

class AllMealsFragmentListeners {

    private lateinit var addMealButton: CardView

    fun initListeners(rootView: View, fragmentManager: FragmentManager) {
        attachViews(rootView)
        attachListeners(fragmentManager)
    }

    private fun attachListeners(fragmentManager: FragmentManager) {
        addMealButton.setOnClickListener {
            MyApp.ingredientsList.clear()
            MyApp.mealName = ""
            val fragment = AddMealFragment.newInstance()
            fragmentManager
                .beginTransaction()
                .setCustomAnimations(
                    R.anim.enter_right_to_left, R.anim.exit_left_to_right,
                    R.anim.enter_left_to_right, R.anim.exit_right_to_left
                )
                .replace(R.id.main_frame_layout, fragment)
                .addToBackStack(AddMealFragment.toString())
                .commit()
        }
    }

    private fun attachViews(rootView: View) {
        addMealButton = rootView.findViewById(R.id.all_meals_add)
    }
}