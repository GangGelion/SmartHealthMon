package com.iid2024.healthmonitor.ui.healthy

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.iid2024.healthmonitor.MainActivity
import com.iid2024.healthmonitor.R
import com.iid2024.healthmonitor.databinding.FragmentMealBinding

/**
 * A simple [Fragment] subclass.
 * Use the [MealFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MealFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var _binding : FragmentMealBinding? = null;
    private val binding get() = _binding!!;

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMealBinding.inflate(inflater, container, false);
        
        binding.mealDay1.setOnClickListener { 
            val bundle = Bundle();
            bundle.putString("day", "1")
            (activity as MainActivity).navigateTo(R.id.action_mealFragment_to_mealContentFragment, bundle)
        }

        binding.mealDay2.setOnClickListener {
            val bundle = Bundle();
            bundle.putString("day", "2")
            (activity as MainActivity).navigateTo(R.id.action_mealFragment_to_mealContentFragment, bundle)
        }

        binding.mealDay3.setOnClickListener {
            val bundle = Bundle();
            bundle.putString("day", "3")
            (activity as MainActivity).navigateTo(R.id.action_mealFragment_to_mealContentFragment, bundle)
        }

        binding.mealDay4.setOnClickListener {
            val bundle = Bundle();
            bundle.putString("day", "4")
            (activity as MainActivity).navigateTo(R.id.action_mealFragment_to_mealContentFragment, bundle)
        }

        binding.mealDay5.setOnClickListener {
            val bundle = Bundle();
            bundle.putString("day", "5")
            (activity as MainActivity).navigateTo(R.id.action_mealFragment_to_mealContentFragment, bundle)
        }

        binding.mealDay6.setOnClickListener {
            val bundle = Bundle();
            bundle.putString("day", "6")
            (activity as MainActivity).navigateTo(R.id.action_mealFragment_to_mealContentFragment, bundle)
        }

        binding.mealDay7.setOnClickListener {
            val bundle = Bundle();
            bundle.putString("day", "7")
            (activity as MainActivity).navigateTo(R.id.action_mealFragment_to_mealContentFragment, bundle)
        }
        
        return binding.root
    }
}