package com.partos.whattoeat.logic.listeners

import android.view.View
import androidx.cardview.widget.CardView
import androidx.fragment.app.FragmentManager
import com.partos.whattoeat.MyApp
import com.partos.whattoeat.R
import com.partos.whattoeat.fragments.meal.AddMealCategoryFragment

class AllMealsCategoryFragmentListeners {

    private lateinit var addMealButton: CardView

    fun initListeners(rootView: View, fragmentManager: FragmentManager, typeId: Long) {
        attachViews(rootView)
        attachListeners(fragmentManager, typeId)
    }

    private fun attachListeners(fragmentManager: FragmentManager, typeId: Long) {
        addMealButton.setOnClickListener {
            MyApp.ingredientsList.clear()
            MyApp.mealName = ""
            val fragment = AddMealCategoryFragment.newInstance(typeId)
            fragmentManager
                .beginTransaction()
                .setCustomAnimations(
                    R.anim.enter_right_to_left, R.anim.exit_left_to_right,
                    R.anim.enter_left_to_right, R.anim.exit_right_to_left
                )
                .replace(R.id.main_frame_layout, fragment)
                .addToBackStack(AddMealCategoryFragment.toString())
                .commit()
        }
    }

    private fun attachViews(rootView: View) {
        addMealButton = rootView.findViewById(R.id.all_meals_category_add)
    }
}