package com.partos.whattoeat.logic.meal.logic

import android.view.View
import androidx.fragment.app.FragmentManager
import com.partos.whattoeat.logic.meal.listeners.AddMealTypeFragmentListeners

class AddMealTypeFragmentLogic {

    fun initFragment(rootView: View, fragmentManager: FragmentManager) {
        AddMealTypeFragmentListeners().initListeners(rootView, fragmentManager)
    }
}