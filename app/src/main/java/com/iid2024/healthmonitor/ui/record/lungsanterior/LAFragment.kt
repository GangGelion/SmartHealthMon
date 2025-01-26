package com.iid2024.healthmonitor.ui.record.lungsanterior

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.iid2024.healthmonitor.R
import com.iid2024.healthmonitor.databinding.FragmentAnteriorBinding
import com.iid2024.healthmonitor.ui.record.ViewRecordFragment

class LAFragment : Fragment() {
    private var _binding: FragmentAnteriorBinding? = null
    private val binding get() = _binding!!

    private var viewRecordFragment : ViewRecordFragment? = null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAnteriorBinding.inflate(inflater, container, false)

        viewRecordFragment = ViewRecordFragment();
        val transaction = childFragmentManager.beginTransaction();
        transaction.replace(R.id.record_thing_la, viewRecordFragment!!).commit();

        for (i in 1..8) {
            val id: Int = resources.getIdentifier("LA" + i, "id", requireActivity().packageName)
            val button: Button = binding.root.findViewById<Button>(id)



            button.setOnClickListener {
                binding.recordThingLa.visibility = View.VISIBLE
                binding.infoText.visibility = View.INVISIBLE

                Log.d("Something", "LA" + i)
                viewRecordFragment!!.setRecordingName("lungs anterior " + i)
            }
        }

        return binding.root
    }

}