package com.partos.whattoeat.logic.meal

import android.view.View
import androidx.fragment.app.FragmentManager

class MainFragmentLogic {

    fun initFragment(rootView: View, fragmentManager: FragmentManager) {
        MainFragmentListeners()
            .initListeners(rootView, fragmentManager)
    }
}