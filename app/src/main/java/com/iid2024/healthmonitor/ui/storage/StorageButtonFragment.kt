package com.iid2024.healthmonitor.ui.storage

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.iid2024.healthmonitor.MainActivity
import com.iid2024.healthmonitor.R
import com.iid2024.healthmonitor.databinding.FragmentStorageButtonBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

/**
 * A simple [Fragment] subclass.
 * Use the [StorageButtonFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class StorageButtonFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private var _binding : FragmentStorageButtonBinding? = null;
    private val binding get() = _binding!!;

    private var recordingFileName = "";

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentStorageButtonBinding.inflate(inflater, container, false)

        recordingFileName = requireArguments().getString("FileName")!!.replace(".3gp", "");

        val timestamp = recordingFileName.split("time")[1]
        val dateString = recordingFileName.split("time")[0] + timestamp.split(" ")[0].replace("_", "/")
        val timeString = timestamp.split(" ")[1].replace("_", ":")

        binding.fileTitle.text = dateString
        binding.fileTimestamp.text = timeString

        binding.root.setOnClickListener {
            val argBundle = Bundle()
            argBundle.putString("FileName", requireArguments().getString("FileName"))

            (activity as MainActivity).navigateTo(R.id.playerFragment, argBundle)
        }

        return binding.root
    }

}