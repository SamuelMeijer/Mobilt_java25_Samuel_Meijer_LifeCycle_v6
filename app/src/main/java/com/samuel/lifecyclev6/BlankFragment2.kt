package com.samuel.lifecyclev6

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [BlankFragment2.newInstance] factory method to
 * create an instance of this fragment.
 */
class BlankFragment2 : Fragment(), SensorEventListener {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    // Components
    private var textX: TextView? = null;
    private var textY: TextView? = null;
    private var textZ: TextView? = null;
    // Sensors
    private lateinit var sensorManager: SensorManager;
    private var acc: Sensor? = null

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
        return inflater.inflate(R.layout.fragment_blank2, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        textX = view.findViewById<TextView>(R.id.textX)
        textY = view.findViewById<TextView>(R.id.textY)
        textZ = view.findViewById<TextView>(R.id.textZ)

        sensorManager = requireContext().getSystemService(Context.SENSOR_SERVICE) as SensorManager
        acc = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)


        // Informing user that Step Counter is not available
        Toast.makeText(
            requireContext(),
            "Step Counter is not available on this device, using Accelerometer instead",
            Toast.LENGTH_LONG
        ).show()
    }

    override fun onResume() {
        super.onResume()

        if (acc != null) {
            sensorManager.registerListener(
                this,
                acc,
                SensorManager.SENSOR_DELAY_NORMAL
            )
        }
    }

    override fun onPause() {
        super.onPause()

        sensorManager.unregisterListener(this)
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        // Not used in this project
    }

    override fun onSensorChanged(event: SensorEvent) {
        if (event.sensor.type == Sensor.TYPE_ACCELEROMETER) {
            val xValue = event.values[0]
            val yValue = event.values[1]
            val zValue = event.values[2]

            textX?.text = "X: $xValue"
            textY?.text = "Y: $yValue"
            textZ?.text = "Z: $zValue"
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment BlankFragment2.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            BlankFragment2().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}