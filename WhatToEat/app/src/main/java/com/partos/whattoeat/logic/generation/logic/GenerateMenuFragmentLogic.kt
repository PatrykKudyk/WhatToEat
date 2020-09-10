package com.partos.whattoeat.logic.generation.logic

import android.view.View
import androidx.fragment.app.FragmentManager
import com.partos.whattoeat.logic.generation.listeners.GenerateMenuFragmentListeners

class GenerateMenuFragmentLogic {

    fun initFragment(rootView: View, fragmentManager: FragmentManager) {
        GenerateMenuFragmentListeners().initListeners(rootView, fragmentManager)
    }
}