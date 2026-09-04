package com.samuel.lifecyclev6

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.google.android.material.textfield.TextInputEditText

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [BlankFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class BlankFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    // Components
    private lateinit var heightText: TextInputEditText
    private lateinit var weightText: TextInputEditText
    private lateinit var bmiText: TextView

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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_blank, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //TODO: Add components for user input
        /*
        DONE:
        Weight: TextInputEditText
        Height: TextInputEditText
        => SHOW BMI

        TODO:
        Date of Birth: DatePickerDialog
        Wake up Time: TimePickerDialog
        Favorite Color: Spinner?
        Right or Left handed: Switch/Toggle/Radiobuttons
         */

        heightText = view.findViewById<TextInputEditText>(R.id.heightInputText)
        weightText = view.findViewById<TextInputEditText>(R.id.weightInputText)
        bmiText = view.findViewById<TextView>(R.id.bmiText)

        // TextWatcher to calculate BMI when input has been given
        val textWatcher = object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                // TODO("Not yet implemented")
            }

            override fun beforeTextChanged(
                s: CharSequence?,
                start: Int,
                count: Int,
                after: Int
            ) {
                // TODO("Not yet implemented")
            }

            override fun onTextChanged(
                s: CharSequence?,
                start: Int,
                before: Int,
                count: Int
            ) {
                calculateBmi()
            }
        }

        heightText.addTextChangedListener(textWatcher)
        weightText.addTextChangedListener(textWatcher)
    }

    private fun calculateBmi() {
        val cm = heightText.text.toString().trim().toDoubleOrNull()
        val kg = weightText.text.toString().trim().toDoubleOrNull()

        var numbOfErrors = 0;
        if (cm == null || cm < 0) {
            heightText.error = "Height needs to be greater than 0"
            numbOfErrors++
        }
        if (kg == null || kg < 0) {
            weightText.error = "Weight needs to be greater than 0"
            numbOfErrors++
        }

        if (numbOfErrors == 0) {
            val m = cm?.div(100)
            val bmi = kg?.div((m!!.times(m)))

            val bmiString = String.format("%.2f", bmi)
            bmiText.text = "BMI: $bmiString"
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment BlankFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            BlankFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}