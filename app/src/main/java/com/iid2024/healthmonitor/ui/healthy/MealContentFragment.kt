package com.iid2024.healthmonitor.ui.healthy

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.iid2024.healthmonitor.R
import com.iid2024.healthmonitor.databinding.FragmentMealBinding
import com.iid2024.healthmonitor.databinding.FragmentMealContentBinding

/**
 * A simple [Fragment] subclass.
 * Use the [MealContentFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MealContentFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var _binding : FragmentMealContentBinding? = null;
    private val binding get() = _binding!!;

    private val noteText : List<String> = listOf(
        "NOTE:\nEnsure sufficient protein intake\nFocus on protein, fiber, and complex carbohydrates. Stay well-hydrated.",
        "NOTE:\nIncrease fiber and healthy fats\nRich in nutrients and fiber, incorporate almonds for healthy fats.",
        "NOTE:\nFocus on complex carbohydrates\nMaintain your protein and carbohydrate intake.",
        "NOTE:\nMonitor calorie intake\nEnsure portion sizes aren't excessive\n",
        "NOTE:\nStay well-hydrated\nKeep up with protein intake using yogurt and tofu.",
        "NOTE:\nDistribute meals throughout the day\nVary your meal menu and include healthy fats from avocado and almonds.",
        "NOTE:\nVary protein sources\nEnsure adequate hydration throughout the day\n"
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentMealContentBinding.inflate(inflater, container, false);

        val day = requireArguments().getString("day")!!

        binding.eatContentTitle.text = "DAY ${day}"
        binding.eatContentNote.text = noteText[day.toIntOrNull()!!]

        binding.breakfast.setImageResource(resources.getIdentifier("d${day}bf", "drawable", requireActivity().packageName))
        binding.lunch.setImageResource(resources.getIdentifier("d${day}ln", "drawable", requireActivity().packageName))
        binding.dinner.setImageResource(resources.getIdentifier("d${day}dn", "drawable", requireActivity().packageName))
        binding.snack.setImageResource(resources.getIdentifier("d${day}sn", "drawable", requireActivity().packageName))

        return binding.root

    }
}