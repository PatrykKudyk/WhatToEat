package com.partos.whattoeat.logic.listeners

import android.content.Context
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.FragmentManager
import com.partos.whattoeat.R
import com.partos.whattoeat.db.DataBaseHelper
import com.partos.whattoeat.logic.ToastHelper

class AddMealTypeFragmentListeners {

    private lateinit var nameEditText: EditText
    private lateinit var addButton: Button

    fun initListeners(rootView: View, fragmentManager: FragmentManager) {
        attachViews(rootView)
        attachListeners(fragmentManager, rootView.context)
    }

    private fun attachListeners(fragmentManager: FragmentManager, context: Context) {
        addButton.setOnClickListener {
            if (nameEditText.text.toString() != "") {
                val db = DataBaseHelper(context)
                db.addMealType(nameEditText.text.toString())
                fragmentManager
                    .popBackStack()
            } else {
                ToastHelper().noNameGiven(context)
            }
        }
    }

    private fun attachViews(rootView: View) {
        nameEditText = rootView.findViewById(R.id.add_meal_type_name)
        addButton = rootView.findViewById(R.id.add_meal_type_button)
    }
}