package com.iid2024.healthmonitor.ui.bmi

import android.animation.Animator
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.MarginLayoutParams
import android.view.inputmethod.InputMethodManager
import android.widget.AdapterView
import android.widget.EditText
import android.widget.Spinner
import androidx.core.animation.addListener
import androidx.core.view.updateLayoutParams
import com.iid2024.healthmonitor.MainActivity
import com.iid2024.healthmonitor.R
import com.iid2024.healthmonitor.databinding.FragmentBmiBinding
import kotlin.math.pow
import kotlin.math.round

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [BmiFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class BmiFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private var _binding : FragmentBmiBinding? = null;
    private val binding get() = _binding!!;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        
        _binding = FragmentBmiBinding.inflate(inflater, container, false)

        val heightDropdown: Spinner = binding.heightDropdown
        var density = resources.displayMetrics.density
        val inputManager = (activity as MainActivity).getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

        heightDropdown.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            var currentItem = heightDropdown.selectedItem.toString()

            override fun onNothingSelected(parent: AdapterView<*>?) {
                Log.d("hi", "no")
                return;
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val newItem = parent?.getItemAtPosition(position).toString()
                if (newItem == "ft, in") {
                    val heightInput : EditText = binding.heightInput
                    val heightInputSecondary : EditText = binding.heightInput2

                    ValueAnimator.ofFloat(0f, 1f).apply {
                        duration = 500
                        val origWidth = heightInput.layoutParams.width / density
                        addUpdateListener(object : ValueAnimator.AnimatorUpdateListener {
                            override fun onAnimationUpdate(animation: ValueAnimator) {
                                val progress: Float = animation.getAnimatedValue() as Float

                                val layoutParams = heightInput.layoutParams
                                val marginLayout = layoutParams as MarginLayoutParams
                                marginLayout.marginEnd = ((95f) * density * progress).toInt()
                                layoutParams.width = ((origWidth + (95f - origWidth) * progress) * density).toInt()

                                heightInput.layoutParams = layoutParams

                            }
                        })

                        start()
                    }

                    ObjectAnimator.ofFloat(heightInputSecondary, "alpha", 1f).apply {
                        duration = 200
                        startDelay = 400
                        heightInputSecondary.visibility = View.VISIBLE
                        start()
                    }
                } else if (currentItem == "ft, in") {
                    val heightInput : EditText = binding.heightInput
                    val heightInputSecondary : EditText = binding.heightInput2

                    ValueAnimator.ofFloat(0f, 1f).apply {
                        duration = 500
                        val origWidth = heightInput.layoutParams.width / density
                        addUpdateListener(object : ValueAnimator.AnimatorUpdateListener {

                            override fun onAnimationUpdate(animation: ValueAnimator) {
                                val progress: Float = animation.getAnimatedValue() as Float

                                val layoutParams = heightInput.layoutParams
                                val marginLayout = layoutParams as MarginLayoutParams
                                marginLayout.marginEnd = ((95f) * density * (1-progress)).toInt()
                                layoutParams.width = ((origWidth + (205f - origWidth) * progress) * density).toInt()

                                heightInput.layoutParams = layoutParams

                            }
                        })

                        start()
                    }
                    ObjectAnimator.ofFloat(heightInputSecondary, "alpha", 0f).apply {
                        duration = 200
                        this.addListener(onEnd = {
                            heightInputSecondary.visibility = View.INVISIBLE
                        })
                        start()
                    }
                }
                currentItem = newItem
            }
        }

        binding.calculateButton.setOnClickListener {
            inputManager.hideSoftInputFromWindow(binding.root.windowToken, 0)

            val weightInput = binding.weightInput
            val heightInput = binding.heightInput
            val weightType = binding.weightDropdown.selectedItem.toString()
            val heightType = binding.heightDropdown.selectedItem.toString()

            if (heightInput.text.toString() == "" || weightInput.text.toString() == "") {
                binding.BMIResultInfo.text = "Please enter a valid number"
                return@setOnClickListener
            }

            val weightMultiplier = when (weightType) { "kg" -> 1f; "lbs" -> 0.453592f; else -> 1f}
            val weight = weightInput.text.toString().toFloat() * weightMultiplier
            var height = 1f
            var heightMultiplier = 1f
            if (heightType == "cm") {
                heightMultiplier = 0.01f
            }

            height = if (heightType == "ft, in") {
                heightInput.text.toString().toFloat() * 0.3048f + binding.heightInput2.text.toString().toFloat() * 0.0254f
            } else {
                heightInput.text.toString().toFloat() * heightMultiplier
            }

            Calculate(weight, height)
        }
        // Inflate the layout for this fragment
        return binding.root
    }

    fun Calculate(weight: Float, height: Float) { // in kg and m
        val bmi = weight / height.pow(2)
        var text: String = ""
        if (bmi < 18.5) {
            text = "underweight"
        } else if (18.5 <= bmi && bmi < 25) {
            text = "healthy"
        } else if (25 <= bmi && bmi < 30) {
            text = "overweight"
        } else if (30 < bmi) {
            text = "obese"
        }

        binding.BMINumber.text = (round(bmi * 100) /100).toString()
        binding.BMIResultInfo.text = "You are considered as " + text
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment BmiFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            BmiFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}