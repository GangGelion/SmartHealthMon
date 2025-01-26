package com.iid2024.healthmonitor.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.iid2024.healthmonitor.MainActivity
import com.iid2024.healthmonitor.databinding.FragmentHomeBinding
import com.iid2024.healthmonitor.R

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this)[HomeViewModel::class.java]

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val recordButton: ImageButton = binding.recordButton
        val bmiButton: ImageButton = binding.bmiButton

        val activity: MainActivity = activity as MainActivity

        recordButton.setOnClickListener{
            activity.navigateTo(R.id.home_to_record)
        }

        binding.storageButton.setOnClickListener {
            activity.navigateTo(R.id.home_to_storage)
        }

        bmiButton.setOnClickListener {
            activity.navigateTo(R.id.home_to_bmi)
        }

        binding.lifestyleButton.setOnClickListener{
            activity.navigateTo(R.id.action_nav_home_to_healthyLivingFragment)
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}