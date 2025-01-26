package com.iid2024.healthmonitor.ui.storage

import android.Manifest
import android.content.pm.PackageManager
import android.media.AudioFormat
import android.media.AudioRecord
import android.media.MediaRecorder
import android.os.Bundle
import android.os.Environment
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver.OnGlobalLayoutListener
import android.widget.LinearLayout
import android.widget.Toast
import androidx.core.R
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.iid2024.healthmonitor.databinding.FragmentStorageBinding
import com.iid2024.healthmonitor.packages.filemanager.FileWriter
import com.iid2024.healthmonitor.packages.filemanager.MicRecord
import com.iid2024.healthmonitor.ui.home.HomeViewModel
import java.io.File

/**
 * A simple [Fragment] subclass.
 * Use the [StorageFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class StorageFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var _binding : FragmentStorageBinding? = null;

    private val binding get() = _binding!!;

    private var recorder : MicRecord = MicRecord();

    val SAMPLE_RATE = 44100
    val CHANNEL_CONFIG = AudioFormat.CHANNEL_IN_STEREO
    val AUDIO_FORMAT = AudioFormat.ENCODING_PCM_16BIT
    val BUFFER_SIZE_RECORDING = AudioRecord.getMinBufferSize(SAMPLE_RATE, CHANNEL_CONFIG, AUDIO_FORMAT)

    var buttonBase : LinearLayout? = null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    fun startRecording() {
        recorder.StartRecording(FileWriter.CreateFile("TestOutput.3gp"))
        Handler(Looper.getMainLooper()).postDelayed({
            recorder.StopRecording()
            Toast.makeText(context, "Finished recording", Toast.LENGTH_SHORT).show()
        }, 5000)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val fileArray = FileWriter.GetFiles() ?: return

        val fragment = childFragmentManager.findFragmentById(binding.linearScroll.id);
        if (fragment != null) {
            return;
        }


        for (file in fileArray) {
            val buttonFragment = StorageButtonFragment()
            val argBundle = Bundle()
            argBundle.putString("FileName", file.name)
            buttonFragment.arguments = argBundle

            childFragmentManager.beginTransaction().add(binding.linearScroll.id, buttonFragment).commit()
            Log.d("F", file.name)
        }
        Log.d("F", binding.linearScroll.childCount.toString())

        super.onViewCreated(view, savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this)[HomeViewModel::class.java]

        _binding = FragmentStorageBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onDestroyView() {
        Log.d("F", binding.linearScroll.childCount.toString())
        binding.linearScroll.removeAllViews()
        super.onDestroyView()
    }

}