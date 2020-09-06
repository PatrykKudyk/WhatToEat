package com.partos.whattoeat.db

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns
import com.partos.whattoeat.models.Ingredient
import com.partos.whattoeat.models.Meal
import com.partos.whattoeat.models.MealType

object TableInfo : BaseColumns {
    const val DATABASE_NAME = "WhatToEat"
    const val TABLE_NAME_MEAL_TYPE = "MealType"
    const val TABLE_COLUMN_NAME = "name"
    const val TABLE_NAME_MEAL = "Meal"
    const val TABLE_COLUMN_TYPE_ID = "typeId"
    const val TABLE_NAME_INGREDIENT = "Ingredient"
    const val TABLE_COLUMN_TYPE = "type"
    const val TABLE_COLUMN_AMOUNT = "amount"
    const val TABLE_COLUMN_MEAL_ID = "mealId"
}

object BasicCommand {
    const val SQL_CREATE_TABLE_MEAL_TYPE =
        "CREATE TABLE ${TableInfo.TABLE_NAME_MEAL_TYPE} (" +
                "${BaseColumns._ID} INTEGER PRIMARY KEY," +
                "${TableInfo.TABLE_COLUMN_NAME} TEXT NOT NULL)"

    const val SQL_CREATE_TABLE_MEAL =
        "CREATE TABLE ${TableInfo.TABLE_NAME_MEAL} (" +
                "${BaseColumns._ID} INTEGER PRIMARY KEY," +
                "${TableInfo.TABLE_COLUMN_NAME} TEXT NOT NULL," +
                "${TableInfo.TABLE_COLUMN_TYPE_ID} INTEGER NOT NULL)"

    const val SQL_CREATE_TABLE_INGREDIENT =
        "CREATE TABLE ${TableInfo.TABLE_NAME_INGREDIENT} (" +
                "${BaseColumns._ID} INTEGER PRIMARY KEY," +
                "${TableInfo.TABLE_COLUMN_NAME} TEXT NOT NULL," +
                "${TableInfo.TABLE_COLUMN_AMOUNT} TEXT NOT NULL," +
                "${TableInfo.TABLE_COLUMN_TYPE} TEXT NOT NULL," +
                "${TableInfo.TABLE_COLUMN_MEAL_ID} INTEGER NOT NULL)"

    const val SQL_DELETE_TABLE_MEAL_TYPE =
        "DROP TABLE IF EXISTS ${TableInfo.TABLE_NAME_MEAL_TYPE}"
    const val SQL_DELETE_TABLE_MEAL = "DROP TABLE IF EXISTS ${TableInfo.TABLE_NAME_MEAL}"
    const val SQL_DELETE_TABLE_INGREDIENT =
        "DROP TABLE IF EXISTS ${TableInfo.TABLE_NAME_INGREDIENT}"

}

class DataBaseHelper(context: Context) :
    SQLiteOpenHelper(context, TableInfo.DATABASE_NAME, null, 1) {
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(BasicCommand.SQL_CREATE_TABLE_MEAL_TYPE)
        db?.execSQL(BasicCommand.SQL_CREATE_TABLE_MEAL)
        db?.execSQL(BasicCommand.SQL_CREATE_TABLE_INGREDIENT)
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        db?.execSQL(BasicCommand.SQL_DELETE_TABLE_MEAL_TYPE)
        db?.execSQL(BasicCommand.SQL_DELETE_TABLE_MEAL)
        db?.execSQL(BasicCommand.SQL_DELETE_TABLE_INGREDIENT)
        onCreate(db)
    }

    fun getMealTypeList(): ArrayList<MealType> {
        var mealTypeList = ArrayList<MealType>()
        val db = readableDatabase
        val selectQuery = "Select * from ${TableInfo.TABLE_NAME_MEAL_TYPE}"
        val result = db.rawQuery(selectQuery, null)
        if (result.moveToFirst()) {
            do {
                var mealType = MealType(
                    result.getLong(result.getColumnIndex(BaseColumns._ID)),
                    result.getString(result.getColumnIndex(TableInfo.TABLE_COLUMN_NAME))
                )
                mealTypeList.add(mealType)
            } while (result.moveToNext())
        }
        result.close()
        db.close()
        return mealTypeList
    }

    fun addMealType(name: String) {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(TableInfo.TABLE_COLUMN_NAME, name)
        db.insert(TableInfo.TABLE_NAME_MEAL_TYPE, null, values)
        db.close()
    }

    fun updateMealType(mealType: MealType) {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(TableInfo.TABLE_COLUMN_NAME, mealType.name)
        db.update(
            TableInfo.TABLE_NAME_MEAL_TYPE, values, BaseColumns._ID + "=?",
            arrayOf(mealType.id.toString())
        )
        db.close()
    }

    fun deleteMealType(mealTypeId: Long): Boolean {
        val db = this.writableDatabase
        val success =
            db.delete(
                TableInfo.TABLE_NAME_MEAL_TYPE,
                BaseColumns._ID + "=?",
                arrayOf(mealTypeId.toString())
            )
                .toLong()
        db.close()
        return Integer.parseInt("$success") != -1
    }


    fun getMealList(): ArrayList<Meal> {
        var mealList = ArrayList<Meal>()
        val db = readableDatabase
        val selectQuery = "Select * from ${TableInfo.TABLE_NAME_MEAL}"
        val result = db.rawQuery(selectQuery, null)
        if (result.moveToFirst()) {
            do {
                var meal = Meal(
                    result.getLong(result.getColumnIndex(BaseColumns._ID)),
                    result.getString(result.getColumnIndex(TableInfo.TABLE_COLUMN_NAME)),
                    result.getLong(result.getColumnIndex(TableInfo.TABLE_COLUMN_TYPE_ID))
                )
                mealList.add(meal)
            } while (result.moveToNext())
        }
        result.close()
        db.close()
        return mealList
    }

    fun getMealList(typeId: Long): ArrayList<Meal> {
        var mealList = ArrayList<Meal>()
        val db = readableDatabase
        val selectQuery = "Select * from ${TableInfo.TABLE_NAME_MEAL} where ${TableInfo.TABLE_COLUMN_TYPE_ID} = " +
                typeId.toString()
        val result = db.rawQuery(selectQuery, null)
        if (result.moveToFirst()) {
            do {
                var meal = Meal(
                    result.getLong(result.getColumnIndex(BaseColumns._ID)),
                    result.getString(result.getColumnIndex(TableInfo.TABLE_COLUMN_NAME)),
                    result.getLong(result.getColumnIndex(TableInfo.TABLE_COLUMN_TYPE_ID))
                )
                mealList.add(meal)
            } while (result.moveToNext())
        }
        result.close()
        db.close()
        return mealList
    }

    fun addMeal(name: String, typeId: Long) {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(TableInfo.TABLE_COLUMN_NAME, name)
        values.put(TableInfo.TABLE_COLUMN_TYPE_ID, typeId)
        db.insert(TableInfo.TABLE_NAME_MEAL, null, values)
        db.close()
    }

    fun updateMeal(meal: Meal) {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(TableInfo.TABLE_COLUMN_NAME, meal.name)
        values.put(TableInfo.TABLE_COLUMN_TYPE_ID, meal.typeId)
        db.update(
            TableInfo.TABLE_NAME_MEAL, values, BaseColumns._ID + "=?",
            arrayOf(meal.id.toString())
        )
        db.close()
    }

    fun deleteMeal(mealId: Long): Boolean {
        val db = this.writableDatabase
        val success =
            db.delete(
                TableInfo.TABLE_NAME_MEAL,
                BaseColumns._ID + "=?",
                arrayOf(mealId.toString())
            )
                .toLong()
        db.close()
        return Integer.parseInt("$success") != -1
    }

    fun getIngredientList(mealId: Long): ArrayList<Ingredient> {
        var ingredientList = ArrayList<Ingredient>()
        val db = readableDatabase
        val selectQuery = "Select * from ${TableInfo.TABLE_NAME_INGREDIENT} where ${TableInfo.TABLE_COLUMN_MEAL_ID} = " +
                mealId.toString()
        val result = db.rawQuery(selectQuery, null)
        if (result.moveToFirst()) {
            do {
                var ingredient = Ingredient(
                    result.getLong(result.getColumnIndex(BaseColumns._ID)),
                    result.getString(result.getColumnIndex(TableInfo.TABLE_COLUMN_NAME)),
                    result.getString(result.getColumnIndex(TableInfo.TABLE_COLUMN_AMOUNT)).toDouble(),
                    result.getString(result.getColumnIndex(TableInfo.TABLE_COLUMN_TYPE)),
                    result.getLong(result.getColumnIndex(TableInfo.TABLE_COLUMN_MEAL_ID))
                )
                ingredientList.add(ingredient)
            } while (result.moveToNext())
        }
        result.close()
        db.close()
        return ingredientList
    }

    fun addIngredient(ingredient: Ingredient) {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(TableInfo.TABLE_COLUMN_NAME, ingredient.name)
        values.put(TableInfo.TABLE_COLUMN_AMOUNT, ingredient.amount.toString())
        values.put(TableInfo.TABLE_COLUMN_TYPE, ingredient.type)
        values.put(TableInfo.TABLE_COLUMN_MEAL_ID, ingredient.mealId)
        db.insert(TableInfo.TABLE_NAME_INGREDIENT, null, values)
        db.close()
    }

    fun updateIngredient(ingredient: Ingredient) {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(TableInfo.TABLE_COLUMN_NAME, ingredient.name)
        values.put(TableInfo.TABLE_COLUMN_AMOUNT, ingredient.amount.toString())
        values.put(TableInfo.TABLE_COLUMN_TYPE, ingredient.type)
        values.put(TableInfo.TABLE_COLUMN_MEAL_ID, ingredient.mealId)
        db.update(
            TableInfo.TABLE_NAME_INGREDIENT, values, BaseColumns._ID + "=?",
            arrayOf(ingredient.id.toString())
        )
        db.close()
    }

    fun deleteIngredient(ingredientId: Long): Boolean {
        val db = this.writableDatabase
        val success =
            db.delete(
                TableInfo.TABLE_NAME_INGREDIENT,
                BaseColumns._ID + "=?",
                arrayOf(ingredientId.toString())
            )
                .toLong()
        db.close()
        return Integer.parseInt("$success") != -1
    }
}
