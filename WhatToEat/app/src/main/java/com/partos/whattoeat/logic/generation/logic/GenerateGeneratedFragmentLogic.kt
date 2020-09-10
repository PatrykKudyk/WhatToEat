package com.partos.whattoeat.logic.generation.logic

import android.content.Context
import android.view.View
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.partos.whattoeat.MyApp
import com.partos.whattoeat.R
import com.partos.whattoeat.adapters.MarginItemDecoration
import com.partos.whattoeat.adapters.recycler.MealsStaticRecyclerViewAdapter
import com.partos.whattoeat.db.DataBaseHelper
import com.partos.whattoeat.models.Meal
import kotlin.random.Random

class GenerateGeneratedFragmentLogic {

    private lateinit var recyclerView: RecyclerView
    private lateinit var mealList: ArrayList<Meal>

    fun initFragment(rootView: View, fragmentManager: FragmentManager, allowDuplicates: Boolean) {
        attachViews(rootView)
        generateList(allowDuplicates, rootView.context)
        attachRecyclerView(rootView.context)
    }

    private fun attachRecyclerView(context: Context) {
        val mLayoutManager = LinearLayoutManager(context)
        recyclerView.layoutManager = mLayoutManager
        recyclerView.addItemDecoration(MarginItemDecoration(12))
        recyclerView.adapter = MealsStaticRecyclerViewAdapter(mealList)
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
                for (i in 0 until mealType.wanted) {
                    val random = Random.nextInt(0, allMeals.size)
                    mealList.add(allMeals[random])
                }
            }
        }
    }

    private fun attachViews(rootView: View) {
        recyclerView = rootView.findViewById(R.id.generate_generated_recycler_view)
    }
}