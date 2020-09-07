package com.partos.whattoeat.logic.meal.logic

import android.view.View
import androidx.fragment.app.FragmentManager
import com.partos.whattoeat.logic.meal.listeners.MealsFragmentListeners

class MealsFragmentLogic {

    fun initFragment(rootView: View, fragmentManager: FragmentManager) {
        MealsFragmentListeners().initListeners(rootView, fragmentManager)
    }
}