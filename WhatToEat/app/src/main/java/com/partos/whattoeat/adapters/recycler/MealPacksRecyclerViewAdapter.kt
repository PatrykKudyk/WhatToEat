package com.partos.whattoeat.adapters.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.partos.whattoeat.R
import com.partos.whattoeat.activities.MainActivity
import com.partos.whattoeat.db.DataBaseHelper
import com.partos.whattoeat.fragments.generation.GenerateMealsFromPackFragment
import com.partos.whattoeat.fragments.meal.MealShowFragment
import com.partos.whattoeat.logic.ToastHelper
import com.partos.whattoeat.models.MealPack
import kotlinx.android.synthetic.main.row_meal.view.*
import kotlinx.android.synthetic.main.row_meal_extended.view.*


class MealPacksRecyclerViewAdapter(var mealPacksList: ArrayList<MealPack>) :
    RecyclerView.Adapter<MealPacksViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealPacksViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val cell = inflater.inflate(R.layout.row_meal_extended, parent, false)
        return MealPacksViewHolder(cell)
    }

    override fun getItemCount(): Int {
        return mealPacksList.size
    }

    override fun onBindViewHolder(holder: MealPacksViewHolder, position: Int) {
        val constraintNormal = holder.view.row_meal_extended_constraint_normal
        val constraintDelete = holder.view.row_meal_extended_constraint_delete
        val constraintEdit = holder.view.row_meal_extended_constraint_edit

        constraintNormal.visibility = View.VISIBLE
        constraintDelete.visibility = View.GONE
        constraintEdit.visibility = View.GONE

        holder.view.row_meal_extended_name.text = mealPacksList[position].name
        holder.view.row_meal_extended_card.setOnClickListener {
            val fragment = GenerateMealsFromPackFragment.newInstance(
                mealPacksList[position].id,
                mealPacksList[position].name
            )
            (holder.view.context as MainActivity).supportFragmentManager
                .beginTransaction()
                .setCustomAnimations(
                    R.anim.enter_right_to_left, R.anim.exit_left_to_right,
                    R.anim.enter_left_to_right, R.anim.exit_right_to_left
                )
                .replace(R.id.main_frame_layout, fragment)
                .addToBackStack(GenerateMealsFromPackFragment.toString())
                .commit()
        }

        holder.view.row_meal_extended_delete.setOnClickListener {
            constraintNormal.visibility = View.GONE
            constraintDelete.visibility = View.VISIBLE
        }
        holder.view.row_meal_extended_yes.setOnClickListener {
            constraintNormal.visibility = View.VISIBLE
            constraintDelete.visibility = View.GONE
            val db = DataBaseHelper(holder.view.context)
            val mealsFromPack = db.getMealsFromPackList(mealPacksList[position].id)
            for (meal in mealsFromPack) {
                db.deleteMealFromPack(meal.id)
            }
            db.deleteMealPack(mealPacksList[position].id)
            mealPacksList.removeAt(position)
            notifyItemRemoved(position)
            notifyItemRangeChanged(position, mealPacksList.size)
        }
        holder.view.row_meal_extended_no.setOnClickListener {
            constraintNormal.visibility = View.VISIBLE
            constraintDelete.visibility = View.GONE
        }
        holder.view.row_meal_extended_edit.setOnClickListener {
            constraintEdit.visibility = View.VISIBLE
            constraintNormal.visibility = View.GONE
            holder.view.row_meal_extended_edit_name.setText(
                holder.view.row_meal_extended_name.text
            )
        }
        holder.view.row_meal_extended_edit_save.setOnClickListener {
            if (holder.view.row_meal_extended_edit_name.text.toString() != "") {
                val db = DataBaseHelper(holder.view.context)
                mealPacksList[holder.adapterPosition].name =
                    holder.view.row_meal_extended_edit_name.text.toString()
                db.updateMealPack(mealPacksList[position])
                holder.view.row_meal_extended_name.text =
                    holder.view.row_meal_extended_edit_name.text
                constraintEdit.visibility = View.GONE
                constraintNormal.visibility = View.VISIBLE
            } else {
                ToastHelper().noNameGiven(holder.view.context)
            }
        }
    }

}

class MealPacksViewHolder(val view: View) : RecyclerView.ViewHolder(view)