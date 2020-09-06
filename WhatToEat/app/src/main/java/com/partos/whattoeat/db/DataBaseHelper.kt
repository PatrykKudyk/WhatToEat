package com.partos.flashback.db

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns
import com.partos.todoapp.models.Date
import com.partos.todoapp.models.ToDo

object TableInfo : BaseColumns {
    const val DATABASE_NAME = "ToDoDB"
    const val TABLE_NAME_TO_DO = "Todo"
    const val TABLE_COLUMN_TO_DO_DATE = "dateId"
    const val TABLE_COLUMN_TO_DO_TEXT = "text"
    const val TABLE_COLUMN_TO_DO_IS_DONE = "isDone"
    const val TABLE_NAME_DATE = "Date"
    const val TABLE_COLUMN_DAY = "day"
    const val TABLE_COLUMN_MONTH = "month"
    const val TABLE_COLUMN_YEAR = "year"
    const val TABLE_COLUMN_DAY_OF_WEEK = "dayOfWeek"
}

object BasicCommand {
    const val SQL_CREATE_TABLE_TO_DO =
        "CREATE TABLE ${TableInfo.TABLE_NAME_TO_DO} (" +
                "${BaseColumns._ID} INTEGER PRIMARY KEY," +
                "${TableInfo.TABLE_COLUMN_TO_DO_DATE} INTEGER KEY," +
                "${TableInfo.TABLE_COLUMN_TO_DO_TEXT} TEXT NOT NULL," +
                "${TableInfo.TABLE_COLUMN_TO_DO_IS_DONE} INTEGER NOT NULL)"

    const val SQL_CREATE_TABLE_DATE =
        "CREATE TABLE ${TableInfo.TABLE_NAME_DATE} (" +
                "${BaseColumns._ID} INTEGER PRIMARY KEY," +
                "${TableInfo.TABLE_COLUMN_DAY} INTEGER NOT NULL," +
                "${TableInfo.TABLE_COLUMN_MONTH} INTEGER NOT NULL," +
                "${TableInfo.TABLE_COLUMN_YEAR} INTEGER NOT NULL," +
                "${TableInfo.TABLE_COLUMN_DAY_OF_WEEK} INTEGER NOT NULL)"

    const val SQL_DELETE_TABLE_TO_DO = "DROP TABLE IF EXISTS ${TableInfo.TABLE_NAME_TO_DO}"
    const val SQL_DELETE_TABLE_DATE = "DROP TABLE IF EXISTS ${TableInfo.TABLE_NAME_DATE}"

}

class DataBaseHelper(context: Context) :
    SQLiteOpenHelper(context, TableInfo.DATABASE_NAME, null, 2) {
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(BasicCommand.SQL_CREATE_TABLE_TO_DO)
        db?.execSQL(BasicCommand.SQL_CREATE_TABLE_DATE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        db?.execSQL(BasicCommand.SQL_DELETE_TABLE_TO_DO)
        db?.execSQL(BasicCommand.SQL_DELETE_TABLE_DATE)
        onCreate(db)
    }

    fun initMonth(month: Int, year: Int) {
        var special = false
        if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0) {
            special = true
        }
        for (day in 1..31) {
            if (month == 2) {
                if (special && day <= 29) {
                    addDate(day, month, year, getDayOfWeek(day, month, year))
                } else if (day <= 28) {
                    addDate(day, month, year, getDayOfWeek(day, month, year))
                }
            } else if (month == 4 || month == 6 || month == 9 || month == 11) {
                if (day <= 30) {
                    addDate(day, month, year, getDayOfWeek(day, month, year))
                }
            } else {
                addDate(day, month, year, getDayOfWeek(day, month, year))
            }
        }
    }

    private fun getDayOfWeek(day: Int, month: Int, year: Int): Int {
        var days = 0
        for (number in 2020..year) {
            if (number != year) {
                if ((number % 4 == 0 && number % 100 != 0) || number % 400 == 0) {
                    days += 366
                } else {
                    days += 365
                }
            } else {
                if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0) {
                    days += getDays(false, month, day)
                } else {
                    days += getDays(true, month, day)
                }
            }
        }
        return when (days % 7) {
            0 -> 2
            1 -> 3
            2 -> 4
            3 -> 5
            4 -> 6
            5 -> 7
            6 -> 1
            else -> 0
        }
    }

    private fun getDays(normal: Boolean, month: Int, day: Int): Int {
        if (normal) {
            when (month) {
                1 -> return day
                2 -> return 31 + day
                3 -> return 59 + day
                4 -> return 90 + day
                5 -> return 120 + day
                6 -> return 151 + day
                7 -> return 181 + day
                8 -> return 212 + day
                9 -> return 243 + day
                10 -> return 273 + day
                11 -> return 304 + day
                12 -> return 334 + day
            }
        } else {
            when (month) {
                1 -> return day
                2 -> return 31 + day
                3 -> return 60 + day
                4 -> return 91 + day
                5 -> return 121 + day
                6 -> return 152 + day
                7 -> return 182 + day
                8 -> return 213 + day
                9 -> return 244 + day
                10 -> return 274 + day
                11 -> return 305 + day
                12 -> return 335 + day
            }
        }
        return 0
    }


    fun getToDoList(dateId: Long): ArrayList<ToDo> {
        var toDoList = ArrayList<ToDo>()
        val db = readableDatabase
        val selectQuery = "Select * from ${TableInfo.TABLE_NAME_TO_DO} where ${TableInfo.TABLE_COLUMN_TO_DO_DATE} = " +
                dateId.toString()
        val result = db.rawQuery(selectQuery, null)
        if (result.moveToFirst()) {
            do {
                var myToDo = ToDo(
                    result.getLong(result.getColumnIndex(BaseColumns._ID)),
                    result.getLong(result.getColumnIndex(TableInfo.TABLE_COLUMN_TO_DO_DATE)),
                    result.getString(result.getColumnIndex(TableInfo.TABLE_COLUMN_TO_DO_TEXT)),
                    result.getInt(result.getColumnIndex(TableInfo.TABLE_COLUMN_TO_DO_IS_DONE))
                )
                toDoList.add(myToDo)
            } while (result.moveToNext())
        }
        result.close()
        db.close()
        return toDoList
    }

    fun addToDo(text: String, dateId: Long) {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(TableInfo.TABLE_COLUMN_TO_DO_DATE, dateId)
        values.put(TableInfo.TABLE_COLUMN_TO_DO_TEXT, text)
        values.put(TableInfo.TABLE_COLUMN_TO_DO_IS_DONE, 0)
        db.insert(TableInfo.TABLE_NAME_TO_DO, null, values)
        db.close()
    }

    fun updateToDo(toDo: ToDo) {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(TableInfo.TABLE_COLUMN_TO_DO_DATE, toDo.dateId)
        values.put(TableInfo.TABLE_COLUMN_TO_DO_TEXT, toDo.text)
        values.put(TableInfo.TABLE_COLUMN_TO_DO_IS_DONE, toDo.isDone)
        db.update(
            TableInfo.TABLE_NAME_TO_DO, values, BaseColumns._ID + "=?",
            arrayOf(toDo.id.toString())
        )
        db.close()
    }

    fun deleteToDo(toDoId: Long): Boolean {
        val db = this.writableDatabase
        val success =
            db.delete(
                TableInfo.TABLE_NAME_TO_DO,
                BaseColumns._ID + "=?",
                arrayOf(toDoId.toString())
            )
                .toLong()
        db.close()
        return Integer.parseInt("$success") != -1
    }

    fun getDateList(): ArrayList<Date> {
        var dateList = ArrayList<Date>()
        val db = readableDatabase
        val selectQuery = "Select * from ${TableInfo.TABLE_NAME_DATE}"
        val result = db.rawQuery(selectQuery, null)
        if (result.moveToFirst()) {
            do {
                val date = Date(
                    result.getInt(result.getColumnIndex(BaseColumns._ID)).toLong(),
                    result.getInt(result.getColumnIndex(TableInfo.TABLE_COLUMN_DAY)),
                    result.getInt(result.getColumnIndex(TableInfo.TABLE_COLUMN_MONTH)),
                    result.getInt(result.getColumnIndex(TableInfo.TABLE_COLUMN_YEAR)),
                    result.getInt(result.getColumnIndex(TableInfo.TABLE_COLUMN_DAY_OF_WEEK))
                )
                dateList.add(date)
            } while (result.moveToNext())
        }
        result.close()
        db.close()
        return dateList
    }

    fun getDateList(fromYear: Int, toYear: Int): ArrayList<Date> {
        var dateList = ArrayList<Date>()
        val db = readableDatabase
        val selectQuery =
            "Select * from ${TableInfo.TABLE_NAME_DATE} where ${TableInfo.TABLE_COLUMN_YEAR} >= " +
                    fromYear.toString() + " and ${TableInfo.TABLE_COLUMN_YEAR} <=" + toYear.toString()
        val result = db.rawQuery(selectQuery, null)
        if (result.moveToFirst()) {
            do {
                val date = Date(
                    result.getInt(result.getColumnIndex(BaseColumns._ID)).toLong(),
                    result.getInt(result.getColumnIndex(TableInfo.TABLE_COLUMN_DAY)),
                    result.getInt(result.getColumnIndex(TableInfo.TABLE_COLUMN_MONTH)),
                    result.getInt(result.getColumnIndex(TableInfo.TABLE_COLUMN_YEAR)),
                    result.getInt(result.getColumnIndex(TableInfo.TABLE_COLUMN_DAY_OF_WEEK))
                )
                dateList.add(date)
            } while (result.moveToNext())
        }
        result.close()
        db.close()
        return dateList
    }

    fun getMonth(month: Int, year: Int): ArrayList<Date> {
        var dateList = ArrayList<Date>()
        val db = readableDatabase
        val selectQuery =
            "Select * from ${TableInfo.TABLE_NAME_DATE} where ${TableInfo.TABLE_COLUMN_MONTH} = " + month.toString() +
                    " and ${TableInfo.TABLE_COLUMN_YEAR} = " + year.toString()
        val result = db.rawQuery(selectQuery, null)
        if (result.moveToFirst()) {
            do {
                val date = Date(
                    result.getInt(result.getColumnIndex(BaseColumns._ID)).toLong(),
                    result.getInt(result.getColumnIndex(TableInfo.TABLE_COLUMN_DAY)),
                    result.getInt(result.getColumnIndex(TableInfo.TABLE_COLUMN_MONTH)),
                    result.getInt(result.getColumnIndex(TableInfo.TABLE_COLUMN_YEAR)),
                    result.getInt(result.getColumnIndex(TableInfo.TABLE_COLUMN_DAY_OF_WEEK))
                )
                dateList.add(date)
            } while (result.moveToNext())
        }

        result.close()
        db.close()
        return dateList
    }

    fun getDate(day: Int, month: Int, year: Int): Date {
        var dateList = ArrayList<Date>()
        val db = readableDatabase
        val selectQuery =
            "Select * from ${TableInfo.TABLE_NAME_DATE} where ${TableInfo.TABLE_COLUMN_DAY} = " +
                    day.toString() + " and ${TableInfo.TABLE_COLUMN_MONTH} = " + month.toString() +
                    " and ${TableInfo.TABLE_COLUMN_YEAR} = " + year.toString()
        val result = db.rawQuery(selectQuery, null)
        if (result.moveToFirst()) {
            do {
                val date = Date(
                    result.getInt(result.getColumnIndex(BaseColumns._ID)).toLong(),
                    result.getInt(result.getColumnIndex(TableInfo.TABLE_COLUMN_DAY)),
                    result.getInt(result.getColumnIndex(TableInfo.TABLE_COLUMN_MONTH)),
                    result.getInt(result.getColumnIndex(TableInfo.TABLE_COLUMN_YEAR)),
                    result.getInt(result.getColumnIndex(TableInfo.TABLE_COLUMN_DAY_OF_WEEK))
                )
                dateList.add(date)
            } while (result.moveToNext())
        }

        result.close()
        db.close()
        return dateList[0]
    }

    fun getDate(dateId: Long): Date {
        var dateList = ArrayList<Date>()
        val db = readableDatabase
        val selectQuery =
            "Select * from ${TableInfo.TABLE_NAME_DATE} where ${BaseColumns._ID} = " +
                    dateId.toString()
        val result = db.rawQuery(selectQuery, null)
        if (result.moveToFirst()) {
            do {
                val date = Date(
                    result.getInt(result.getColumnIndex(BaseColumns._ID)).toLong(),
                    result.getInt(result.getColumnIndex(TableInfo.TABLE_COLUMN_DAY)),
                    result.getInt(result.getColumnIndex(TableInfo.TABLE_COLUMN_MONTH)),
                    result.getInt(result.getColumnIndex(TableInfo.TABLE_COLUMN_YEAR)),
                    result.getInt(result.getColumnIndex(TableInfo.TABLE_COLUMN_DAY_OF_WEEK))
                )
                dateList.add(date)
            } while (result.moveToNext())
        }

        result.close()
        db.close()
        return dateList[0]
    }


    fun addDate(day: Int, month: Int, year: Int, dayOfWeek: Int): Boolean {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(TableInfo.TABLE_COLUMN_DAY, day)
        values.put(TableInfo.TABLE_COLUMN_MONTH, month)
        values.put(TableInfo.TABLE_COLUMN_YEAR, year)
        values.put(TableInfo.TABLE_COLUMN_DAY_OF_WEEK, dayOfWeek)
        val success = db.insert(TableInfo.TABLE_NAME_DATE, null, values)
        db.close()
        return (Integer.parseInt("$success") != -1)
    }

    fun updateDate(date: Date): Boolean {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(TableInfo.TABLE_COLUMN_DAY, date.day)
        values.put(TableInfo.TABLE_COLUMN_MONTH, date.month)
        values.put(TableInfo.TABLE_COLUMN_YEAR, date.year)
        values.put(TableInfo.TABLE_COLUMN_DAY_OF_WEEK, date.dayOfWeek)
        val success = db.update(
            TableInfo.TABLE_NAME_DATE, values, BaseColumns._ID + "=?",
            arrayOf(date.id.toString())
        ).toLong()
        return Integer.parseInt("$success") != -1
    }
}