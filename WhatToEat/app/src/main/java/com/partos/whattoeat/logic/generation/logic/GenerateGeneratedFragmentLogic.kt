package com.partos.whattoeat.logic.generation.logic

import android.content.Context
import android.view.View
import android.widget.LinearLayout
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.partos.whattoeat.MyApp
import com.partos.whattoeat.R
import com.partos.whattoeat.adapters.MarginItemDecoration
import com.partos.whattoeat.adapters.recycler.MealsStaticRecyclerViewAdapter
import com.partos.whattoeat.db.DataBaseHelper
import com.partos.whattoeat.logic.generation.listeners.GenerateGeneratedFragmentListeners
import com.partos.whattoeat.models.Amount
import com.partos.whattoeat.models.Ingredient
import com.partos.whattoeat.models.Meal
import kotlin.random.Random

class GenerateGeneratedFragmentLogic {

    private lateinit var recyclerView: RecyclerView
    private lateinit var saveLayout: LinearLayout
    private lateinit var noSaveLayout: LinearLayout

    fun initFragment(rootView: View, fragmentManager: FragmentManager, allowDuplicates: Boolean) {
        attachViews(rootView)
        attachRecyclerView(rootView.context)
        setLayout()
        generateIngredientList(rootView.context)
        GenerateGeneratedFragmentListeners().initListeners(rootView, fragmentManager)
    }

    private fun generateIngredientList(context: Context) {
        val db = DataBaseHelper(context)
        var ingredients: ArrayList<Ingredient>
        MyApp.ingredientsList.clear()
        MyApp.ingredientsMap.clear()
        for (meal in MyApp.mealList) {
            ingredients = db.getIngredientList(meal.id)
            for (ingredient in ingredients) {
                MyApp.ingredientsList.add(ingredient)
            }
        }
        generateIngredientsMap()
    }

    private fun generateIngredientsMap() {
        val ingredientsMap = HashMap<String, HashMap<String, Double>>()
        for (ingredient in MyApp.ingredientsList) {
            val name = ingredient.name.trim().toLowerCase()
            if (ingredientsMap.containsKey(name)) {
                val ingredientsMapEntity = ingredientsMap[name]
                val type = ingredient.type.trim().toLowerCase()
                val amounts: HashMap<String, Double>
                if (ingredientsMapEntity?.containsKey(type)!!) {
                    amounts = ingredientsMap.getValue(name)
                    amounts[type] = amounts[type]?.plus(ingredient.amount) as Double
                } else {
                    amounts = HashMap()
                    amounts[type] = ingredient.amount
                }
                ingredientsMap[name] = amounts
            } else {
                val amounts = HashMap<String, Double>()
                amounts[ingredient.type] = ingredient.amount
                ingredientsMap[name] = amounts
            }
        }
        MyApp.ingredientsMap = ingredientsMap
    }

    private fun setLayout() {
        if (MyApp.isSaved) {
            noSaveLayout.visibility = View.VISIBLE
            saveLayout.visibility = View.GONE
        } else {
            noSaveLayout.visibility = View.GONE
            saveLayout.visibility = View.VISIBLE
        }
    }

    private fun attachRecyclerView(context: Context) {
        val mLayoutManager = LinearLayoutManager(context)
        recyclerView.layoutManager = mLayoutManager
        recyclerView.addItemDecoration(MarginItemDecoration(12))
        recyclerView.adapter = MealsStaticRecyclerViewAdapter(MyApp.mealList)
    }


    private fun attachViews(rootView: View) {
        recyclerView = rootView.findViewById(R.id.generate_generated_recycler_view)
        saveLayout = rootView.findViewById(R.id.generate_generated_linear_save)
        noSaveLayout = rootView.findViewById(R.id.generate_generated_linear_no_save)
    }
}