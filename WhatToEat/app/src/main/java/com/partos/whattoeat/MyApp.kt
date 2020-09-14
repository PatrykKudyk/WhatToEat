package com.partos.whattoeat

import android.app.Application
import com.partos.whattoeat.models.*

class MyApp : Application() {
    companion object {
        var ingredientsList = ArrayList<Ingredient>()
        var mealName = ""
        var typesList = ArrayList<MealTypeGeneration>()
        var allowDuplicates = false
        var isSaved = false
        var ingredientsMap = HashMap<String, HashMap<String, Double>>()
        var mealList = ArrayList<Meal>()
    }
}