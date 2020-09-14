package com.partos.whattoeat.logic.meal.listeners

import android.content.Context
import android.view.View
import android.widget.EditText
import androidx.cardview.widget.CardView
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.partos.whattoeat.MyApp
import com.partos.whattoeat.R
import com.partos.whattoeat.adapters.recycler.IngredientsRecyclerViewAdapter
import com.partos.whattoeat.db.DataBaseHelper
import com.partos.whattoeat.logic.ToastHelper
import com.partos.whattoeat.models.Ingredient

class AddMealCategoryFragmentListeners {

    private lateinit var saveButton: CardView
    private lateinit var addIngredientButton: CardView
    private lateinit var nameEditText: EditText
    private lateinit var recyclerView: RecyclerView

    fun initListeners(rootView: View, fragmentManager: FragmentManager, typeId: Long) {
        attachViews(rootView)
        attachListeners(fragmentManager, rootView.context, typeId)
    }

    private fun attachListeners(fragmentManager: FragmentManager, context: Context, typeId: Long) {
        saveButton.setOnClickListener {
            if (nameEditText.text.toString() != "") {
                if (!isNameAlreadyTaken(context)) {
                    if (MyApp.ingredientsList.size != 0) {
                        if (areIngredientsFilled()) {
                            MyApp.mealName = nameEditText.text.toString()
                            val db = DataBaseHelper(context)
                            db.addMeal(MyApp.mealName, typeId)
                            val meal = db.getMealList(MyApp.mealName)[0]
                            for (ingredient in MyApp.ingredientsList){
                                db.addIngredient(Ingredient(
                                    0,
                                    ingredient.name,
                                    ingredient.amount,
                                    ingredient.type,
                                    meal.id
                                ))
                            }
                            fragmentManager
                                .popBackStack()
                        } else {
                            ToastHelper().ingredientsIncomplete(context)
                        }
                    } else {
                        ToastHelper().noIngredients(context)
                    }
                } else {
                    ToastHelper().nameAlreadyExists(context)
                }
            } else {
                ToastHelper().noNameGiven(context)
            }
        }
        addIngredientButton.setOnClickListener {
            MyApp.ingredientsList.add(Ingredient(0,"", 0.0, "", 0))
            recyclerView.adapter = IngredientsRecyclerViewAdapter(MyApp.ingredientsList)
        }
    }

    private fun isNameAlreadyTaken(context: Context): Boolean {
        val db = DataBaseHelper(context)
        val meals = db.getMealList(nameEditText.text.toString())
        return meals.size != 0
    }

    private fun areIngredientsFilled(): Boolean {
        for (ingredient in MyApp.ingredientsList) {
            if (ingredient.name == "" || ingredient.amount.toString() == "" || ingredient.type == "") {
                return false
            }
        }
        return true
    }

    private fun attachViews(rootView: View) {
        saveButton = rootView.findViewById(R.id.add_meal_category_save)
        addIngredientButton = rootView.findViewById(R.id.add_meal_category_add)
        nameEditText = rootView.findViewById(R.id.add_meal_category_edit_text_name)
        recyclerView = rootView.findViewById(R.id.add_meal_category_recycler)
    }
}