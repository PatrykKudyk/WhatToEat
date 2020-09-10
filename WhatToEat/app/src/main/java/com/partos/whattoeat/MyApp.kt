package com.partos.whattoeat

import android.app.Application
import com.partos.whattoeat.models.Ingredient
import com.partos.whattoeat.models.MealType
import com.partos.whattoeat.models.MealTypeGeneration

class MyApp : Application() {
    companion object {
        var ingredientsList = ArrayList<Ingredient>()
        var mealName = ""
        var typesList = ArrayList<MealTypeGeneration>()
        var allowDuplicates = false
        var isSaved = false
    }
}