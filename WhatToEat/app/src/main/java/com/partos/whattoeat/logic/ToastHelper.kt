package com.partos.whattoeat.logic

import android.content.Context
import android.widget.Toast
import com.partos.whattoeat.R

class ToastHelper {

    fun noNameGiven(context: Context) {
        Toast.makeText(context, context.getText(R.string.toast_no_name), Toast.LENGTH_SHORT).show()
    }

    fun noIngredients(context: Context) {
        Toast.makeText(context, context.getText(R.string.toast_no_ingredients), Toast.LENGTH_SHORT)
            .show()
    }

    fun nameAlreadyExists(context: Context) {
        Toast.makeText(context, context.getText(R.string.toast_name_exists), Toast.LENGTH_SHORT)
            .show()
    }

    fun ingredientsIncomplete(context: Context) {
        Toast.makeText(
            context,
            context.getText(R.string.toast_incomplete_ingredients),
            Toast.LENGTH_SHORT
        ).show()
    }

    fun cantDeleteAllIngredients(context: Context) {
        Toast.makeText(
            context,
            context.getText(R.string.toast_cant_delete_ingredient),
            Toast.LENGTH_SHORT
        ).show()
    }

    fun mealTypeAlreadyChosen(context: Context) {
        Toast.makeText(
            context,
            context.getText(R.string.toast_meal_type_already_chosen),
            Toast.LENGTH_SHORT
        ).show()
    }

    fun mealTypeEmpty(context: Context) {
        Toast.makeText(context, context.getText(R.string.toast_meal_type_empty), Toast.LENGTH_SHORT)
            .show()
    }

    fun numberIncorrect(context: Context, typeName: String) {
        Toast.makeText(
            context,
            (context.getString(R.string.toast_meal_type_number_incorrect1) + " " + typeName + " " +
                    context.getString(R.string.toast_meal_type_number_incorrect2)),
            Toast.LENGTH_SHORT
        ).show()
    }

    fun noMealTypesGiven(context: Context) {
        Toast.makeText(context, context.getText(R.string.toast_no_meal_types), Toast.LENGTH_SHORT)
            .show()
    }

    fun noMealsGiven(context: Context) {
        Toast.makeText(context, context.getText(R.string.toast_no_meals), Toast.LENGTH_SHORT).show()
    }

    fun successfullySaved(context: Context) {
        Toast.makeText(
            context,
            context.getText(R.string.toast_successfully_saved),
            Toast.LENGTH_SHORT
        ).show()
    }
}