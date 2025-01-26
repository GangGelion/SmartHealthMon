package com.iid2024.healthmonitor.ui.slideshow

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.iid2024.healthmonitor.databinding.FragmentChatBinding
import com.iid2024.healthmonitor.databinding.FragmentSlideshowBinding

class ChatFragment : Fragment() {

    private var _binding: FragmentChatBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentChatBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.openChatbot
        binding.openChatbot.setOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://wa.me/628983138381?text=.ai hi")))
        }
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://wa.me/628983138381?text=.ai hi")))
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}