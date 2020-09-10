package com.partos.whattoeat.logic.generation.logic

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.partos.whattoeat.MyApp
import com.partos.whattoeat.R
import com.partos.whattoeat.adapters.MarginItemDecoration
import com.partos.whattoeat.adapters.recycler.IngredientsMapRecyclerViewAdapter
import com.partos.whattoeat.models.Amount
import com.partos.whattoeat.models.IngredientExtended

class GenerateIngredientsFragmentLogic {

    private lateinit var recyclerView: RecyclerView

    fun initFragment(rootView: View) {
        attachViews(rootView)
        attachRecyclerView(rootView.context)
    }

    private fun attachRecyclerView(context: Context) {
        val mLayoutManager = LinearLayoutManager(context)
        recyclerView.layoutManager = mLayoutManager
        recyclerView.addItemDecoration(MarginItemDecoration(12))
        recyclerView.adapter =
            IngredientsMapRecyclerViewAdapter(generateIngredientsExtended(MyApp.ingredientsMap))
    }

    private fun generateIngredientsExtended(ingredientsMap: HashMap<String, ArrayList<Amount>>): ArrayList<IngredientExtended> {
        val ingredientsList = ArrayList<IngredientExtended>()
        for (ingredient in ingredientsMap) {
            ingredientsList.add(
                IngredientExtended(
                    ingredient.key,
                    ingredient.value
                )
            )
        }
        return ingredientsList
    }

    private fun attachViews(rootView: View) {
        recyclerView = rootView.findViewById(R.id.generate_ingredients_recycler_view)
    }
}