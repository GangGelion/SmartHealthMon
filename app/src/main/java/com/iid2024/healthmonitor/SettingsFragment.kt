package com.iid2024.healthmonitor

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.iid2024.healthmonitor.databinding.FragmentSettingsBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SettingsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SettingsFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var _binding : FragmentSettingsBinding? = null;
    private val binding get() = _binding!!;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSettingsBinding.inflate(inflater, container, false);

        binding.diagnosisUrl.setText((requireActivity() as MainActivity).getSettings("DiagnosisUrl") ?: "")

        binding.diagnosisUrl.setOnFocusChangeListener { _, hasFocus -> 
            if (!hasFocus) {
                (requireActivity() as MainActivity).setSettings("DiagnosisUrl", binding.diagnosisUrl.text.toString());
            }
        }

        binding.forceDiag.isChecked = (requireActivity() as MainActivity).getSettings("DropInvalid").toBoolean() ?: false;

        binding.forceDiag.setOnClickListener { (requireActivity() as MainActivity).setSettings("DropInvalid", binding.forceDiag.isChecked.toString()) }
        // Inflate the layout for this fragment
        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment SettingsFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SettingsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}