package com.iid2024.healthmonitor.ui.record

import android.icu.util.Calendar
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.iid2024.healthmonitor.R
import com.iid2024.healthmonitor.databinding.FragmentViewRecordBinding
import com.iid2024.healthmonitor.packages.filemanager.FileWriter
import com.iid2024.healthmonitor.packages.filemanager.MicRecord
import java.io.File
import java.text.SimpleDateFormat
import kotlin.math.floor

/**
 * A simple [Fragment] subclass.
 * Use the [ViewRecordFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ViewRecordFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private var recordingStarted : Boolean = false;

    private val recorder : MicRecord = MicRecord();

    private var _binding : FragmentViewRecordBinding? = null;
    private val binding get() = _binding!!;

    private var recordingFile : File? = null;
    private var time : Int = 0;

    fun setRecordingName(name: String) {
        binding.recordText.text = "Recording " + name
    }

    fun startRecording() {
        val fileFormat = SimpleDateFormat("dd_MM_yyyy HH_mm_ss", java.util.Locale.getDefault());
        val timeString = fileFormat.format(Calendar.getInstance().time)
        recordingFile = FileWriter.CreateFile(binding.recordText.text.toString() + " time" + timeString + ".3gp")

        recordingStarted = true
        binding.button.text = "STOP"
        recorder.StartRecording(recordingFile!!)
    }

    fun stopRecording(isCancelled : Boolean?) {
        if (!recordingStarted) {
            return
        }
        if (isCancelled == true) {
            Toast.makeText(context, "Recording cancelled", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, "Recording stopped", Toast.LENGTH_SHORT).show()
        }
        recorder.StopRecording()
        recordingStarted = false
        recordingFile = null
        binding.button.text = "START"
        time = 0
        binding.recordLengthText.text = "00:00";
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentViewRecordBinding.inflate(inflater, container, false)

        binding.button.setOnClickListener{
            if (recordingStarted) {
                stopRecording(false);
            } else {
                startRecording();
            }
        }

        object : Thread() {
            override fun run() {
                try {
                    while (true) {
                        if (activity == null) {
                            break
                        }
                        requireActivity().runOnUiThread(object : Runnable {
                            override fun run() {
                                val pos = time.toDouble()
                                val posString = "${String.format("%02d", floor(pos/60).toInt())}:${String.format("%02d", (pos - floor(pos/60)).toInt())}"
                                binding.recordLengthText.text = "${posString}";
                            }
                        })
                        sleep(1000)
                        if (recordingStarted) {
                            time += 1
                        }
                    }
                } catch (e : InterruptedException) {
                    e.printStackTrace()
                }
            }
        }.start()


        return binding.root
    }

    override fun onDestroyView() {
        if (recordingStarted) {
            stopRecording(true);
        }
        super.onDestroyView()
    }
}