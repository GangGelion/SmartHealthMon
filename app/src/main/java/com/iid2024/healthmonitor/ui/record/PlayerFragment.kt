package com.iid2024.healthmonitor.ui.record

import android.media.MediaPlayer
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import com.iid2024.healthmonitor.R
import com.iid2024.healthmonitor.databinding.FragmentPlayerBinding
import com.iid2024.healthmonitor.packages.filemanager.FileWriter
import kotlin.math.floor

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [PlayerFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PlayerFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private var _binding : FragmentPlayerBinding? = null;
    private val binding get() = _binding!!;
    private var fileNameFull : String? = null;

    private val player : MediaPlayer = MediaPlayer();

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPlayerBinding.inflate(inflater, container, false);

        fileNameFull = requireArguments().getString("FileName")!!
        val fileName = requireArguments().getString("FileName")!!.replace(".3gp", "");

        val recordingName = fileName.split(" time")[0]
        val timestamp = fileName.split("time")[1]
        val recordingDate = timestamp.split(" ")[0].replace("_", "/")
        val recordingTime = timestamp.split(" ")[0].replace("_", ":")

        binding.audioTitle.text = recordingName
        binding.audioTime.text = "Recorded on ${recordingDate}\n${recordingTime}"

        player.setDataSource(FileWriter.GetFile(fileNameFull!!)!!.absolutePath);
        player.prepare()




        binding.playerPlayButton.setOnClickListener{
            player.start()
        }

        binding.playerPauseButton.setOnClickListener{
            player.pause()
        }

        binding.playerStopButton.setOnClickListener{
            player.pause()
            player.seekTo(0);
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
                                val length = player.duration.toDouble()/1000
                                val pos = player.currentPosition.toDouble()/1000
                                val lengthString = "${String.format("%02d", floor(length/60).toInt())}:${String.format("%02d", (length - floor(length/60)).toInt())}"
                                val posString = "${String.format("%02d", floor(pos/60).toInt())}:${String.format("%02d", (pos - floor(pos/60)).toInt())}"
                                binding.playerCurrentTime.text = "${posString} / ${lengthString}";
                            }
                        })
                        sleep(100)
                    }
                } catch (e : InterruptedException) {
                    e.printStackTrace()
                }
            }
        }.start()

        return binding.root;
    }
}