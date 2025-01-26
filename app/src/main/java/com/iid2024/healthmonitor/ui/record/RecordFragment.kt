package com.iid2024.healthmonitor.ui.record

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import com.iid2024.healthmonitor.MainActivity
import com.iid2024.healthmonitor.R
import com.iid2024.healthmonitor.databinding.FragmentRecordBinding


/**
 * A simple [Fragment] subclass.
 * Use the [RecordFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class RecordFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private var _binding: FragmentRecordBinding? = null;
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRecordBinding.inflate(inflater, container, false)

        val antButton: ImageButton = binding.AnteriorButton
        val postButton: ImageButton = binding.PosteriorButton

        val activity: MainActivity = activity as MainActivity

        antButton.setOnClickListener {
            activity.navigateTo(R.id.record_to_anterior)
        }

        postButton.setOnClickListener{
            activity.navigateTo(R.id.action_nav_record_to_posteriorFragment)
        }

        binding.HeartButton.setOnClickListener{
            activity.navigateTo(R.id.action_nav_record_to_heartFragment)
        }

        return binding.root
    }

}