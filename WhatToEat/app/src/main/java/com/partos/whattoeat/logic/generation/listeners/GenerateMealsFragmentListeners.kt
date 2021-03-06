package com.partos.whattoeat.logic.generation.listeners

import android.content.Context
import android.view.View
import android.widget.Button
import androidx.cardview.widget.CardView
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.partos.whattoeat.MyApp
import com.partos.whattoeat.R
import com.partos.whattoeat.adapters.recycler.GenerateMealTypesRecyclerViewAdapter
import com.partos.whattoeat.db.DataBaseHelper
import com.partos.whattoeat.fragments.generation.GenerateChooseTypeFragment
import com.partos.whattoeat.fragments.generation.GenerateGeneratedFragment
import com.partos.whattoeat.logic.ToastHelper
import com.partos.whattoeat.models.Meal
import kotlin.random.Random

class GenerateMealsFragmentListeners {

    private lateinit var buttonYes: Button
    private lateinit var buttonNo: Button
    private lateinit var recyclerView: RecyclerView
    private lateinit var addNewButton: CardView
    private lateinit var generateButton: CardView
    private lateinit var mealList: ArrayList<Meal>


    fun initListeners(rootView: View, fragmentManager: FragmentManager) {
        attachViews(rootView)
        attachListeners(fragmentManager, rootView.context)
    }

    private fun attachListeners(fragmentManager: FragmentManager, context: Context) {
        buttonYes.setOnClickListener {
            if (!MyApp.allowDuplicates) {
                MyApp.allowDuplicates = true
                buttonYes.background = context.getDrawable(R.drawable.button_background_red)
                buttonNo.background = context.getDrawable(R.drawable.button_background_red_light)
                recyclerView.adapter = GenerateMealTypesRecyclerViewAdapter(true)
            }
        }
        buttonNo.setOnClickListener {
            if (MyApp.allowDuplicates) {
                MyApp.allowDuplicates = false
                buttonNo.background = context.getDrawable(R.drawable.button_background_red)
                buttonYes.background = context.getDrawable(R.drawable.button_background_red_light)
                recyclerView.adapter = GenerateMealTypesRecyclerViewAdapter(false)
            }
        }
        addNewButton.setOnClickListener {
            val fragment = GenerateChooseTypeFragment.newInstance()
            fragmentManager
                .beginTransaction()
                .setCustomAnimations(
                    R.anim.enter_right_to_left, R.anim.exit_left_to_right,
                    R.anim.enter_left_to_right, R.anim.exit_right_to_left
                )
                .replace(R.id.main_frame_layout, fragment)
                .addToBackStack(GenerateChooseTypeFragment.toString())
                .commit()
        }

        generateButton.setOnClickListener {
            if (areTypesCorrect(context)) {
                MyApp.mealList.clear()
                MyApp.isSaved = false
                MyApp.ingredientsList.clear()
                generateList(MyApp.allowDuplicates, context)
                val fragment = GenerateGeneratedFragment.newInstance(MyApp.allowDuplicates)
                fragmentManager
                    .beginTransaction()
                    .setCustomAnimations(
                        R.anim.enter_right_to_left, R.anim.exit_left_to_right,
                        R.anim.enter_left_to_right, R.anim.exit_right_to_left
                    )
                    .replace(R.id.main_frame_layout, fragment)
                    .addToBackStack(GenerateGeneratedFragment.toString())
                    .commit()
            }
        }
    }

    private fun areTypesCorrect(context: Context): Boolean {
        if (MyApp.typesList.size > 0) {
            for (type in MyApp.typesList) {
                if (MyApp.allowDuplicates) {
                    if (type.wanted <= 0) {
                        ToastHelper().numberIncorrect(context, type.name)
                        return false
                    }
                } else {
                    if (type.wanted <= 0 || (type.wanted > type.max)) {
                        ToastHelper().numberIncorrect(context, type.name)
                        return false
                    }
                }
            }
        } else {
            ToastHelper().noMealTypesGiven(context)
            return false
        }
        return true
    }

    private fun generateList(allowDuplicates: Boolean, context: Context) {
        mealList = ArrayList()
        val db = DataBaseHelper(context)
        if (allowDuplicates) {
            for(mealType in MyApp.typesList){
                val allMeals = db.getMealList(mealType.id)
                for (i in 0 until mealType.wanted) {
                    var random = Random.nextInt(0, allMeals.size)
                    mealList.add(allMeals[random])
                }
            }
        } else {
            for(mealType in MyApp.typesList){
                val allMeals = db.getMealList(mealType.id)
                val chosenList = ArrayList<Boolean>()
                for (i in 0 until allMeals.size) {
                    chosenList.add(false)
                }
                for (i in 0 until mealType.wanted) {
                    var random: Int
                    do {
                        random = Random.nextInt(0, allMeals.size)
                    } while (chosenList[random])
                    mealList.add(allMeals[random])
                    chosenList[random] = true
                }
            }
        }
        MyApp.mealList = mealList
    }

    private fun attachViews(rootView: View) {
        buttonYes = rootView.findViewById(R.id.generate_meals_button_yes)
        buttonNo = rootView.findViewById(R.id.generate_meals_button_no)
        recyclerView = rootView.findViewById(R.id.generate_meals_recycler_view)
        addNewButton = rootView.findViewById(R.id.generate_meals_add)
        generateButton = rootView.findViewById(R.id.generate_meals_generate)
    }
}