package com.iid2024.healthmonitor.ui.record.voicerecorder

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.iid2024.healthmonitor.R
import com.iid2024.healthmonitor.databinding.FragmentVoiceRecorderBinding

/**
 * A simple [Fragment] subclass.
 * Use the [VoiceRecorderFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class VoiceRecorderFragment : Fragment() {
    // TODO: Rename and change types of parameters

    private var _bindings: FragmentVoiceRecorderBinding? = null
    private val bindings get() = _bindings!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _bindings = FragmentVoiceRecorderBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return bindings.root
    }
}