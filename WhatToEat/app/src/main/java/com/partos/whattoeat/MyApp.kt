package com.partos.whattoeat

import android.app.Application
import com.partos.whattoeat.models.Ingredient
import com.partos.whattoeat.models.MealType

class MyApp : Application() {
    companion object {
        var ingredientsList = ArrayList<Ingredient>()
        var mealName = ""
        var typesList = ArrayList<MealType>
    }
}