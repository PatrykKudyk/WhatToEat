package com.partos.whattoeat.db

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns
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

    const val SQL_DELETE_TABLE_MEAL_TYPE = "DROP TABLE IF EXISTS ${TableInfo.TABLE_NAME_MEAL_TYPE}"
    const val SQL_DELETE_TABLE_MEAL = "DROP TABLE IF EXISTS ${TableInfo.TABLE_NAME_MEAL}"
    const val SQL_DELETE_TABLE_INGREDIENT = "DROP TABLE IF EXISTS ${TableInfo.TABLE_NAME_INGREDIENT}"

}

class DataBaseHelper(context: Context) :
    SQLiteOpenHelper(context, TableInfo.DATABASE_NAME, null, 2) {
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

}