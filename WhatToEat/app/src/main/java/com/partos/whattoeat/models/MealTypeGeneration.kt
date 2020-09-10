package com.partos.whattoeat.models

data class MealTypeGeneration (
    val id: Long,
    var name: String,
    var wanted: Int,
    var max: Int
)