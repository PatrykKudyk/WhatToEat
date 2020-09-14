package com.partos.whattoeat.logic.generation.listeners

import android.content.Context
import android.view.View
import androidx.cardview.widget.CardView
import com.partos.whattoeat.R
import com.partos.whattoeat.db.DataBaseHelper
import com.partos.whattoeat.logic.ToastHelper
import com.partos.whattoeat.models.IngredientExtended

class GenerateIngredientsFragmentListeners {

    private lateinit var addToShoppingListButton: CardView

    fun initFragment(rootView: View, ingredientsList: ArrayList<IngredientExtended>) {
        attachViews(rootView)
        attachListeners(ingredientsList, rootView.context)
    }

    private fun attachListeners(
        ingredientsList: java.util.ArrayList<IngredientExtended>,
        context: Context
    ) {
        addToShoppingListButton.setOnClickListener {
            val db = DataBaseHelper(context)
            for (ingredient in ingredientsList) {
                var text = ingredient.name + " "
                for (i in 0 until ingredient.amounts.size) {
                    if (i != 0) {
                        text += ", " + ingredient.amounts[i].amount.toString() + " " + ingredient.amounts[i].type
                    } else {
                        text += ingredient.amounts[i].amount.toString() + " " + ingredient.amounts[i].type
                    }
                }
                db.addToDo(text)
            }
            ToastHelper().successfullyAddedToShoppingList(context)
        }
    }

    private fun attachViews(rootView: View) {
        addToShoppingListButton =
            rootView.findViewById(R.id.generate_ingredients_add_to_shopping_list)
    }
}