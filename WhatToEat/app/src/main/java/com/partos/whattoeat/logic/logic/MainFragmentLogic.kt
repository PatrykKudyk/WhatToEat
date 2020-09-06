package com.partos.whattoeat.logic.logic

import android.view.View
import androidx.fragment.app.FragmentManager
import com.partos.whattoeat.logic.listeners.MainFragmentListeners

class MainFragmentLogic {

    fun initFragment(rootView: View, fragmentManager: FragmentManager) {
        MainFragmentListeners().initListeners(rootView, fragmentManager)
    }
}