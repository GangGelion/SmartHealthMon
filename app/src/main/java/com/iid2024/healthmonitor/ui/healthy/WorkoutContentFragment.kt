package com.iid2024.healthmonitor.ui.healthy

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.iid2024.healthmonitor.databinding.FragmentWorkoutContentBinding

/**
 * A simple [Fragment] subclass.
 * Use the [WorkoutContentFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class WorkoutContentFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private val monday : List<String> = listOf("jj", "pu", "ipu", "dipu", "dpu", "apu", "dips");
    private val tuesday : List<String> = listOf("jj", "pulup", "cgpu", "cup", "apulup", "amepulup", "")
    private val wednesday : List<String> = listOf("jj", "s", "l", "ss", "sup", "lrais", "rutwis")

    private var _binding : FragmentWorkoutContentBinding? = null;
    private val binding get() = _binding!!;


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentWorkoutContentBinding.inflate(inflater, container, false);

        val day = requireArguments().getString("day")
        var desc = ""
        var currentList : List<String> = listOf("");
        if (day == "Monday" || day == "Friday") {
            desc = "Chest, Shoulder and Tricep"
            currentList = monday
        } else if (day == "Tuesday" || day == "Saturday") {
            desc = "Back and Bicep"
            currentList = tuesday
        } else if (day == "Wednesday") {
            desc = "Legs and Abs"
            currentList = wednesday
        }

        binding.workoutDay.text = day;
        binding.workoutType.text = desc;
        binding.firstActivity.setImageResource(requireContext().resources.getIdentifier(currentList[0], "drawable", requireActivity().packageName))
        binding.secondActivity.setImageResource(requireContext().resources.getIdentifier(currentList[1], "drawable", requireActivity().packageName))
        binding.thirdActivity.setImageResource(requireContext().resources.getIdentifier(currentList[2], "drawable", requireActivity().packageName))
        binding.fourthActivity.setImageResource(requireContext().resources.getIdentifier(currentList[3], "drawable", requireActivity().packageName))
        binding.fifthActivity.setImageResource(requireContext().resources.getIdentifier(currentList[4], "drawable", requireActivity().packageName))
        binding.sixthActivity.setImageResource(requireContext().resources.getIdentifier(currentList[5], "drawable", requireActivity().packageName))
        binding.seventhActivity.setImageResource(requireContext().resources.getIdentifier(currentList[6], "drawable", requireActivity().packageName))

        // Inflate the layout for this fragment
        return binding.root
    }

}