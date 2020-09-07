package com.partos.whattoeat.logic.meal.logic

import android.view.View
import androidx.fragment.app.FragmentManager
import com.partos.whattoeat.logic.meal.listeners.MainFragmentListeners

class MainFragmentLogic {

    fun initFragment(rootView: View, fragmentManager: FragmentManager) {
        MainFragmentListeners().initListeners(rootView, fragmentManager)
    }
}