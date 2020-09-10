package com.partos.whattoeat.logic.generation.listeners

import android.view.View
import android.widget.LinearLayout
import androidx.cardview.widget.CardView
import androidx.fragment.app.FragmentManager
import com.partos.whattoeat.R
import com.partos.whattoeat.models.Ingredient
import com.partos.whattoeat.models.Meal

class GenerateGeneratedFragmentListeners {

    private lateinit var saveButton: CardView
    private lateinit var ingredientsButton: CardView
    private lateinit var ingredientsButton2: CardView
    private lateinit var saveLinear: LinearLayout
    private lateinit var noSaveLinear: LinearLayout
    private lateinit var ingredientsList: ArrayList<Ingredient>


    fun initListeners(
        rootView: View,
        fragmentManager: FragmentManager,
        mealsList: ArrayList<Meal>
    ) {
        attachViews(rootView)
        attachListeners(fragmentManager, mealsList)
    }

    private fun attachListeners(fragmentManager: FragmentManager, mealsList: ArrayList<Meal>) {
        saveButton.setOnClickListener {
            saveLinear.visibility = View.GONE
            noSaveLinear.visibility = View.GONE
        }
        ingredientsButton.setOnClickListener {

        }
    }

    private fun attachViews(rootView: View) {
        saveButton = rootView.findViewById(R.id.generate_generated_save)
        ingredientsButton = rootView.findViewById(R.id.generate_generated_ingredients)
        ingredientsButton2 = rootView.findViewById(R.id.generate_generated_ingredients2)
        saveLinear = rootView.findViewById(R.id.generate_generated_linear_save)
        noSaveLinear = rootView.findViewById(R.id.generate_generated_linear_no_save)
    }
}