package com.iid2024.healthmonitor.ui.healthy

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.iid2024.healthmonitor.MainActivity
import com.iid2024.healthmonitor.R
import com.iid2024.healthmonitor.databinding.FragmentHealthyLivingBinding

class HealthyLivingFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private var _binding : FragmentHealthyLivingBinding? = null;
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHealthyLivingBinding.inflate(inflater, container, false);

        binding.workout.setOnClickListener{
            (activity as MainActivity).navigateTo(R.id.action_healthyLivingFragment_to_workoutFragment);
        }

        binding.mealMenu.setOnClickListener {
            (activity as MainActivity).navigateTo(R.id.action_healthyLivingFragment_to_mealFragment);
        }

        // Inflate the layout for this fragment
        return binding.root
    }

}