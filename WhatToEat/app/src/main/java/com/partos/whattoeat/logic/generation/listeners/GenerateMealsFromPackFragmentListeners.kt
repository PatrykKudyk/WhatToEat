package com.partos.whattoeat.logic.generation.listeners

import android.content.Context
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.fragment.app.FragmentManager
import com.partos.whattoeat.MyApp
import com.partos.whattoeat.R
import com.partos.whattoeat.db.DataBaseHelper
import com.partos.whattoeat.fragments.generation.GenerateIngredientsFragment
import com.partos.whattoeat.logic.ToastHelper
import com.partos.whattoeat.models.Ingredient
import com.partos.whattoeat.models.MealPack

class GenerateMealsFromPackFragmentListeners {

    private lateinit var nameText: TextView
    private lateinit var nameEditText: EditText
    private lateinit var saveButton: ImageView
    private lateinit var editLayout: LinearLayout
    private lateinit var generateButton: CardView
    private var currentName = ""

    fun initListeners(rootView: View, mealPackId: Long, fragmentManager: FragmentManager) {
        attachViews(rootView)
        attachListeners(rootView.context, mealPackId, fragmentManager)
    }

    private fun attachListeners(
        context: Context,
        mealPackId: Long,
        fragmentManager: FragmentManager
    ) {
        nameText.setOnClickListener {
            nameText.visibility = View.GONE
            editLayout.visibility = View.VISIBLE
            currentName = nameText.text.toString()
            nameEditText.setText(nameText.text)
        }
        saveButton.setOnClickListener {
            if (nameEditText.text.toString() == currentName) {
                nameText.visibility = View.VISIBLE
                editLayout.visibility = View.GONE
            } else {
                if (nameEditText.text.toString() != "") {
                    val db = DataBaseHelper(context)
                    if (db.getMealPack(nameEditText.text.toString()).size == 0) {
                        db.updateMealPack(
                            MealPack(
                                mealPackId,
                                nameEditText.text.toString()
                            )
                        )
                        nameText.text = nameEditText.text
                        nameText.visibility = View.VISIBLE
                        editLayout.visibility = View.GONE
                    } else {
                        ToastHelper().nameAlreadyExists(context)
                    }
                } else {
                    ToastHelper().noNameGiven(context)
                }
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
                val ingredientsMapEntity = ingredientsMap[name]
                val type = ingredient.type.trim().toLowerCase()
                val amounts: HashMap<String, Double>
                if (ingredientsMapEntity?.containsKey(type)!!) {
                    amounts = ingredientsMap.getValue(name)
                    amounts[type] = amounts[type]?.plus(ingredient.amount) as Double
                } else {
                    amounts = HashMap()
                    amounts[type] = ingredient.amount
                }
                ingredientsMap[name] = amounts
            } else {
                val amounts = HashMap<String, Double>()
                amounts[ingredient.type] = ingredient.amount
                ingredientsMap[name] = amounts
            }
        }
        MyApp.ingredientsMap = ingredientsMap
    }

    private fun attachViews(rootView: View) {
        nameText = rootView.findViewById(R.id.generate_meals_from_pack_text_name)
        nameEditText = rootView.findViewById(R.id.generate_meals_from_pack_edit_text_name)
        saveButton = rootView.findViewById(R.id.generate_meals_from_pack_save)
        editLayout = rootView.findViewById(R.id.generate_meals_from_pack_linear_edit)
        generateButton = rootView.findViewById(R.id.generate_meals_from_pack_generate_ingredients)
    }
}