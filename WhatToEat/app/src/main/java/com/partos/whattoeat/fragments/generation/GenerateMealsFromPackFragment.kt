package com.partos.whattoeat.fragments.generation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.partos.whattoeat.R
import com.partos.whattoeat.logic.generation.logic.GenerateMealsFromPackFragmentLogic

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [GenerateMealsFromPackFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class GenerateMealsFromPackFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var mealPackId: Long? = null
    private var mealPackName: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            mealPackId = it.getLong(ARG_PARAM1)
            mealPackName = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_generate_meals_from_pack, container, false)
        GenerateMealsFromPackFragmentLogic().initFragment(
            view,
            mealPackId as Long,
            mealPackName as String,
            fragmentManager as FragmentManager
        )
        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment GenerateMealsFromPackFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(mealPackId: Long, mealPackName: String) =
            GenerateMealsFromPackFragment().apply {
                arguments = Bundle().apply {
                    putLong(ARG_PARAM1, mealPackId)
                    putString(ARG_PARAM2, mealPackName)
                }
            }
    }
}