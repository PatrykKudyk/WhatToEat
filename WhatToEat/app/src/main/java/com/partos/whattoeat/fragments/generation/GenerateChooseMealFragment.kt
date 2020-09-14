package com.partos.whattoeat.fragments.generation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.partos.whattoeat.R
import com.partos.whattoeat.logic.generation.logic.GenerateChooseMealFragmentLogic

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [GenerateChooseMealFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class GenerateChooseMealFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var mealTypeId: Long? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            mealTypeId = it.getLong(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_generate_choose_meal, container, false)
        GenerateChooseMealFragmentLogic().initFragment(view, mealTypeId as Long)
        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment GenerateChooseMealFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(mealTypeId: Long) =
            GenerateChooseMealFragment().apply {
                arguments = Bundle().apply {
                    putLong(ARG_PARAM1, mealTypeId)
                }
            }
    }
}