package com.iid2024.healthmonitor.packages.filemanager

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.media.AudioFormat
import android.media.AudioRecord
import android.media.MediaRecorder
import androidx.core.app.ActivityCompat
import java.io.File

class MicRecord {
    val SAMPLE_RATE = 44100
    val CHANNEL_CONFIG = AudioFormat.CHANNEL_IN_STEREO
    val AUDIO_FORMAT = AudioFormat.ENCODING_PCM_16BIT
    val BUFFER_SIZE_RECORDING = AudioRecord.getMinBufferSize(SAMPLE_RATE, CHANNEL_CONFIG, AUDIO_FORMAT)

    var recorder : MediaRecorder? = null;

    @SuppressLint("MissingPermission")
    fun StartRecording(output : File) {
        recorder = MediaRecorder();

        recorder!!.setAudioSource(MediaRecorder.AudioSource.MIC)
        recorder!!.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP)
        recorder!!.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_WB)

        recorder!!.setOutputFile(output)
        recorder!!.prepare()
        recorder!!.start()
    }

    fun StopRecording() {
        if (recorder == null) {
            return
        }
        recorder!!.stop()
        recorder!!.release()
        recorder = null
    }

}