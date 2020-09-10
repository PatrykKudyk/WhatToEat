package com.partos.whattoeat.logic.generation.listeners

import android.content.Context
import android.view.View
import android.widget.Button
import androidx.cardview.widget.CardView
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.partos.whattoeat.MyApp
import com.partos.whattoeat.R
import com.partos.whattoeat.adapters.recycler.GenerateMealTypesRecyclerViewAdapter
import com.partos.whattoeat.fragments.generation.GenerateChooseTypeFragment
import com.partos.whattoeat.fragments.generation.GenerateGeneratedFragment
import com.partos.whattoeat.logic.ToastHelper

class GenerateMealsFragmentListeners {

    private lateinit var buttonYes: Button
    private lateinit var buttonNo: Button
    private lateinit var recyclerView: RecyclerView
    private lateinit var addNewButton: CardView
    private lateinit var generateButton: CardView

    fun initListeners(rootView: View, fragmentManager: FragmentManager) {
        attachViews(rootView)
        attachListeners(fragmentManager, rootView.context)
    }

    private fun attachListeners(fragmentManager: FragmentManager, context: Context) {
        buttonYes.setOnClickListener {
            if (!MyApp.allowDuplicates) {
                MyApp.allowDuplicates = true
                buttonYes.background = context.getDrawable(R.drawable.button_background_red)
                buttonNo.background = context.getDrawable(R.drawable.button_background_red_light)
                recyclerView.adapter = GenerateMealTypesRecyclerViewAdapter(true)
            }
        }
        buttonNo.setOnClickListener {
            if (MyApp.allowDuplicates) {
                MyApp.allowDuplicates = false
                buttonNo.background = context.getDrawable(R.drawable.button_background_red)
                buttonYes.background = context.getDrawable(R.drawable.button_background_red_light)
                recyclerView.adapter = GenerateMealTypesRecyclerViewAdapter(false)
            }
        }
        addNewButton.setOnClickListener {
            val fragment = GenerateChooseTypeFragment.newInstance()
            fragmentManager
                .beginTransaction()
                .setCustomAnimations(
                    R.anim.enter_right_to_left, R.anim.exit_left_to_right,
                    R.anim.enter_left_to_right, R.anim.exit_right_to_left
                )
                .replace(R.id.main_frame_layout, fragment)
                .addToBackStack(GenerateChooseTypeFragment.toString())
                .commit()
        }

        generateButton.setOnClickListener {
            if (areTypesCorrect(context)) {
                MyApp.isSaved = false
                MyApp.ingredientsList.clear()
                val fragment = GenerateGeneratedFragment.newInstance(MyApp.allowDuplicates)
                fragmentManager
                    .beginTransaction()
                    .setCustomAnimations(
                        R.anim.enter_right_to_left, R.anim.exit_left_to_right,
                        R.anim.enter_left_to_right, R.anim.exit_right_to_left
                    )
                    .replace(R.id.main_frame_layout, fragment)
                    .addToBackStack(GenerateGeneratedFragment.toString())
                    .commit()
            }
        }
    }

    private fun areTypesCorrect(context: Context): Boolean {
        if (MyApp.typesList.size > 0) {
            for (type in MyApp.typesList) {
                if (MyApp.allowDuplicates) {
                    if (type.wanted <= 0) {
                        ToastHelper().numberIncorrect(context, type.name)
                        return false
                    }
                } else {
                    if (type.wanted <= 0 || (type.wanted > type.max)) {
                        ToastHelper().numberIncorrect(context, type.name)
                        return false
                    }
                }
            }
        } else {
            ToastHelper().noMealsGiven(context)
            return false
        }
        return true
    }

    private fun attachViews(rootView: View) {
        buttonYes = rootView.findViewById(R.id.generate_meals_button_yes)
        buttonNo = rootView.findViewById(R.id.generate_meals_button_no)
        recyclerView = rootView.findViewById(R.id.generate_meals_recycler_view)
        addNewButton = rootView.findViewById(R.id.generate_meals_add)
        generateButton = rootView.findViewById(R.id.generate_meals_generate)
    }
}