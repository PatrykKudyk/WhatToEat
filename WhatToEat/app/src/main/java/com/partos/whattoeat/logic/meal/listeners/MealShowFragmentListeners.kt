package com.partos.whattoeat.logic.meal.listeners

import android.content.Context
import android.view.View
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.partos.whattoeat.R
import com.partos.whattoeat.adapters.recycler.IngredientsEditRecyclerViewAdapter
import com.partos.whattoeat.db.DataBaseHelper
import com.partos.whattoeat.logic.ToastHelper
import com.partos.whattoeat.models.Ingredient

class MealShowFragmentListeners {

    private lateinit var recyclerView: RecyclerView
    private lateinit var addIngredientButton: CardView
    private lateinit var addToShoppingListButton: CardView

    fun initListeners(rootView: View, mealId: Long) {
        attachViews(rootView)
        attachListeners(rootView.context, mealId)
    }

    private fun attachListeners(context: Context, mealId: Long) {
        addIngredientButton.setOnClickListener {
            val db = DataBaseHelper(context)
            db.addIngredient(
                Ingredient(
                    0,
                    context.getString(R.string.new_ingredient),
                    0.0,
                    context.getString(R.string.type),
                    mealId
                )
            )
            recyclerView.adapter = IngredientsEditRecyclerViewAdapter(db.getIngredientList(mealId))
        }
        addToShoppingListButton.setOnClickListener {
            val db = DataBaseHelper(context)
            val ingredients = db.getIngredientList(mealId)
            for (ingredient in ingredients) {
                var text = ingredient.name + " " + ingredient.amount.toString() + " " + ingredient.type
                db.addToDo(text)
            }
            ToastHelper().successfullyAddedToShoppingList(context)
        }
    }

    private fun attachViews(rootView: View) {
        recyclerView = rootView.findViewById(R.id.show_meal_recycler)
        addIngredientButton = rootView.findViewById(R.id.show_meal_button_add_ingredient)
        addToShoppingListButton = rootView.findViewById(R.id.show_meal_button_add_shopping)
    }
}