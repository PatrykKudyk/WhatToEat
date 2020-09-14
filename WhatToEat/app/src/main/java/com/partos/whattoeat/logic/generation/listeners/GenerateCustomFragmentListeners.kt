package com.partos.whattoeat.logic.generation.listeners

import android.content.Context
import android.icu.util.Calendar
import android.view.View
import androidx.cardview.widget.CardView
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.partos.whattoeat.MyApp
import com.partos.whattoeat.R
import com.partos.whattoeat.db.DataBaseHelper
import com.partos.whattoeat.fragments.generation.GenerateIngredientsFragment
import com.partos.whattoeat.logic.ToastHelper
import com.partos.whattoeat.models.Ingredient
import com.partos.whattoeat.models.Meal
import com.partos.whattoeat.models.MealFromPack

class GenerateCustomFragmentListeners {

    private lateinit var recyclerView: RecyclerView
    private lateinit var addButton: CardView
    private lateinit var saveButton: CardView
    private lateinit var generateButton: CardView

    fun initListeners(rootView: View, fragmentManager: FragmentManager) {
        attachViews(rootView)
        attachListeners(fragmentManager, rootView.context)
    }

    private fun attachListeners(fragmentManager: FragmentManager, context: Context) {
        addButton.setOnClickListener {

        }

        saveButton.setOnClickListener {
            if (MyApp.mealList.size != 0) {
                saveMeals(MyApp.mealList ,context)
                ToastHelper().successfullySaved(context)
            } else {
                ToastHelper().noMealsGiven(context)
            }
        }

        generateButton.setOnClickListener {
            if (MyApp.mealList.size != 0) {
                generateIngredientList(context)
                val fragment = GenerateIngredientsFragment.newInstance()
                fragmentManager
                    .beginTransaction()
                    .setCustomAnimations(
                        R.anim.enter_right_to_left, R.anim.exit_left_to_right,
                        R.anim.enter_left_to_right, R.anim.exit_right_to_left
                    )
                    .replace(R.id.main_frame_layout, fragment)
                    .addToBackStack(GenerateIngredientsFragment.toString())
                    .commit()
            } else {
                ToastHelper().noMealsGiven(context)
            }
        }
    }

    private fun generateIngredientList(context: Context) {
        val db = DataBaseHelper(context)
        var ingredients: ArrayList<Ingredient>
        MyApp.ingredientsList.clear()
        MyApp.ingredientsMap.clear()
        for (meal in MyApp.mealList) {
            ingredients = db.getIngredientList(meal.id)
            for (ingredient in ingredients) {
                MyApp.ingredientsList.add(ingredient)
            }
        }
        generateIngredientsMap()
    }

    private fun generateIngredientsMap() {
        val ingredientsMap = HashMap<String, HashMap<String, Double>>()
        for (ingredient in MyApp.ingredientsList) {
            val name = ingredient.name.trim().toLowerCase()
            if (ingredientsMap.containsKey(name)) {
                val type = ingredient.type.trim().toLowerCase()
                val amounts: HashMap<String, Double>
                if (ingredientsMap.containsKey(type)) {
                    amounts = ingredientsMap.getValue(name)
                    amounts[type] = amounts.get(type)?.plus(ingredient.amount) as Double
                } else {
                    amounts = HashMap()
                    amounts.put(type, ingredient.amount)
                }
                ingredientsMap.put(name, amounts)
            } else {
                val amounts = HashMap<String, Double>()
                amounts.put(ingredient.type, ingredient.amount)
                ingredientsMap.put(name, amounts)
            }
        }
        MyApp.ingredientsMap = ingredientsMap
    }

    private fun saveMeals(mealsList: ArrayList<Meal>, context: Context) {
        val db = DataBaseHelper(context)
        val now = Calendar.getInstance()
        val name = now.get(Calendar.YEAR).toString() + "/" + (now.get(Calendar.MONTH) + 1).toString() + "/" +
                now.get(Calendar.DAY_OF_MONTH) + " " + now.get(Calendar.HOUR_OF_DAY) + ":" +
                now.get(Calendar.MINUTE) + ":" + now.get(Calendar.SECOND)
        db.addMealPack(name)
        val mealPack = db.getMealPack(name)[0]
        for(meal in mealsList) {
            db.addMealFromPack(
                MealFromPack(
                    0,
                    mealPack.id,
                    meal.id
                )
            )
        }
    }

    private fun attachViews(rootView: View) {
        recyclerView = rootView.findViewById(R.id.generate_custom_recycler_view)
        addButton = rootView.findViewById(R.id.generate_custom_add)
        saveButton = rootView.findViewById(R.id.generate_custom_save)
        generateButton = rootView.findViewById(R.id.generate_custom_generate)
    }
}