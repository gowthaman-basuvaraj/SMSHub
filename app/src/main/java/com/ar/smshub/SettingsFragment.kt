package com.ar.smshub

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.app.Fragment;
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Switch
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_settings.*
import java.util.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [settings.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [settings.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class SettingsFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var listener: OnFragmentInteractionListener? = null
    var settingsManager = SettingsManager(this.activity)

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
        val view = inflater.inflate(R.layout.fragment_settings, container, false)
        val btnSave: Button = view.findViewById(R.id.btnSave)
        val txtURL: EditText = view.findViewById(R.id.textURL)
        val txtInterval: EditText = view.findViewById(R.id.textInterval)
        val txtDeviceId: EditText = view.findViewById(R.id.textDeviceId)
        val switchIsEnabled: Switch = view.findViewById(R.id.switchIsEnabled)

        txtInterval.setText(settingsManager.interval.toString())
        switchIsEnabled.isChecked = settingsManager.isSendEnabled
        txtURL.setText(settingsManager.URL)
        txtDeviceId.setText(settingsManager.deviceId)

        //save
        btnSave.setOnClickListener {
            settingsManager.setSettings(
                txtInterval.text.toString().toInt(),
                txtURL.text.toString(),
                txtDeviceId.text.toString()
            )
            val fragment = MainFragment()
            val transaction = fragmentManager.beginTransaction()
            transaction.replace(R.id.main_view, fragment)
            transaction.commit()
        }

        //save
        switchIsEnabled.setOnClickListener {
            settingsManager.setIsSendEnabled(switchIsEnabled.isChecked)
        }
        return view
    }

    // TODO: Rename method, update argument and hook method into UI event
    fun onButtonPressed(uri: Uri) {
        listener?.onFragmentInteraction(uri)
    }


    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments]
     * (http://developer.android.com/training/basics/fragments/communicating.html)
     * for more information.
     */
    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment settings.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SettingsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
