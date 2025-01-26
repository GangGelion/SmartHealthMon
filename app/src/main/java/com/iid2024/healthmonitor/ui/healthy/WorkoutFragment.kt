package com.iid2024.healthmonitor.ui.healthy

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.iid2024.healthmonitor.MainActivity
import com.iid2024.healthmonitor.R
import com.iid2024.healthmonitor.databinding.FragmentWorkoutBinding

/**
 * A simple [Fragment] subclass.
 * Use the [WorkoutFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class WorkoutFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private var _binding : FragmentWorkoutBinding? = null;
    private val binding get() = _binding!!;


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentWorkoutBinding.inflate(inflater, container, false);

        binding.monButton.setOnClickListener {
            val argBundle = Bundle()
            argBundle.putString("day", "Monday")
            (activity as MainActivity).navigateTo(R.id.action_workoutFragment_to_workoutContentFragment, argBundle)
        }

        binding.tueButton.setOnClickListener {
            val argBundle = Bundle()
            argBundle.putString("day", "Tuesday")
            (activity as MainActivity).navigateTo(R.id.action_workoutFragment_to_workoutContentFragment, argBundle)
        }

        binding.wedButton.setOnClickListener {
            val argBundle = Bundle()
            argBundle.putString("day", "Wednesday")
            (activity as MainActivity).navigateTo(R.id.action_workoutFragment_to_workoutContentFragment, argBundle)
        }

        binding.friButton.setOnClickListener {
            val argBundle = Bundle()
            argBundle.putString("day", "Friday")
            (activity as MainActivity).navigateTo(R.id.action_workoutFragment_to_workoutContentFragment, argBundle)
        }

        binding.satButton.setOnClickListener {
            val argBundle = Bundle()
            argBundle.putString("day", "Saturday")
            (activity as MainActivity).navigateTo(R.id.action_workoutFragment_to_workoutContentFragment, argBundle)
        }

        return binding.root
    }

}