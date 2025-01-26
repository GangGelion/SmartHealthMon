package com.iid2024.healthmonitor.ui.record.lungsanterior

import android.content.Context
import android.hardware.camera2.CameraManager
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.SurfaceHolder
import android.view.SurfaceView
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.getSystemService
import com.iid2024.healthmonitor.R
import com.iid2024.healthmonitor.databinding.FragmentAnteriorBinding
import com.iid2024.healthmonitor.databinding.FragmentHeartBinding
import com.iid2024.healthmonitor.ui.record.ViewRecordFragment
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import net.kibotu.heartrateometer.HeartRateOmeter

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HeartFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HeartFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private var _binding: FragmentHeartBinding? = null
    private val binding get() = _binding!!
    private var bpmMeter : Disposable? = null;

    private var averageBpm : Number = 70;
    private var fingerDetected : Boolean = true;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    private fun onFingerChanged(status : Boolean) {
        fingerDetected = status
        if (!status) {
            binding.FingerWarningText.text = "Finger not detected"
        } else {
            binding.FingerWarningText.text = " "
        }
    }

    private fun setFlashlightMode(status: Boolean) {
        val cameraManager = requireActivity().getSystemService(Context.CAMERA_SERVICE) as CameraManager
        val cameraId = cameraManager.cameraIdList[0]
        cameraManager.setTorchMode(cameraId, status)
    }

    protected fun startRecording() {
        setFlashlightMode(true)
        bpmMeter = HeartRateOmeter()
            .setFingerDetectionListener(this::onFingerChanged)
            .bpmUpdates(binding.bpmView)
            .subscribe(
                { bpm: HeartRateOmeter.Bpm -> Log.d("hi", "hello"); binding.BPM.text = bpm.value.toString()},
                Throwable::printStackTrace)
    }

    private fun stopRecording() {
        bpmMeter?.dispose()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHeartBinding.inflate(inflater, container, false);

        binding.HeartRecord.setOnClickListener {
            startRecording()
        }

        binding.HeartStop.setOnClickListener {
            stopRecording()
        }

        return binding.root;
    }

    override fun onDestroyView() {
        super.onDestroyView()
        setFlashlightMode(false)
    }
}