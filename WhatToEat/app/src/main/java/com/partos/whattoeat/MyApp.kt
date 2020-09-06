package com.partos.whattoeat

import android.app.Application
import com.partos.whattoeat.models.Ingredient

class MyApp : Application() {
    companion object {
        var ingredientsList = ArrayList<Ingredient>()
        var mealName = ""
    }
}