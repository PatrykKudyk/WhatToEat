package com.partos.whattoeat.logic.generation.listeners

import android.content.Context
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.partos.whattoeat.R
import com.partos.whattoeat.db.DataBaseHelper
import com.partos.whattoeat.logic.ToastHelper
import com.partos.whattoeat.models.MealPack

class GenerateMealsFromPackFragmentListeners {

    private lateinit var nameText: TextView
    private lateinit var nameEditText: EditText
    private lateinit var saveButton: ImageView
    private lateinit var editLayout: LinearLayout
    private var currentName = ""

    fun initListeners(rootView: View, mealPackId: Long) {
        attachViews(rootView)
        attachListeners(rootView.context, mealPackId)
    }

    private fun attachListeners(context: Context, mealPackId: Long) {
        nameText.setOnClickListener {
            nameText.visibility = View.GONE
            editLayout.visibility = View.VISIBLE
            currentName = nameText.text.toString()
            nameEditText.setText(nameText.text)
        }
        saveButton.setOnClickListener {
            if (nameEditText.text.toString() == currentName) {
                nameText.visibility = View.VISIBLE
                editLayout.visibility = View.GONE
            } else {
                if (nameEditText.text.toString() != "") {
                    val db = DataBaseHelper(context)
                    if (db.getMealPack(nameEditText.text.toString()).size == 0) {
                        db.updateMealPack(
                            MealPack(
                                mealPackId,
                                nameEditText.text.toString()
                            )
                        )
                        nameText.text = nameEditText.text
                        nameText.visibility = View.VISIBLE
                        editLayout.visibility = View.GONE
                    } else {
                        ToastHelper().nameAlreadyExists(context)
                    }
                } else {
                    ToastHelper().noNameGiven(context)
                }
            }
        }
    }

    private fun attachViews(rootView: View) {
        nameText = rootView.findViewById(R.id.generate_meals_from_pack_text_name)
        nameEditText = rootView.findViewById(R.id.generate_meals_from_pack_edit_text_name)
        saveButton = rootView.findViewById(R.id.generate_meals_from_pack_save)
        editLayout = rootView.findViewById(R.id.generate_meals_from_pack_linear_edit)
    }
}