package com.partos.whattoeat.models

data class Ingredient(
    var id: Long,
    var name: String,
    var amount: Double,
    var type: String,
    var mealId: Long
)