package com.example.garinnoglobal.fragment

import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.annotation.Nullable
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hatch_i.R
import com.example.hatch_i.adapter.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [DetailsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DetailsFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    lateinit var rv_machdata: RecyclerView
    internal lateinit var view: View
    private val detailsList = ArrayList<DetailsModel>()
    private var TemperatureMeasurementStr: String? =null
    private var TemperatureMeasurementStr1: String? =null
    private lateinit var detailsAdapter: DetailsAdapter
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
    //    return inflater.inflate(R.layout.fragment_details, container, false)
        view = inflater.inflate(R.layout.fragment_details, container, false)
        initView(view)
        TemperatureMeasurementStr = "30" + "\u2109"
        TemperatureMeasurementStr1 = "20" + "\u2109"
        (activity as AppCompatActivity).supportActionBar?.title = "Incubation Details"
        prepareDetailsData()
        return view
    }

    override fun onViewCreated(view: View, @Nullable savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sp_time = view.findViewById<View>(R.id.sp_time) as Spinner

        val sp_incub = view.findViewById<View>(R.id.sp_incub) as Spinner
        sp_incub.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                (parent.getChildAt(0) as TextView).setTextColor(Color.WHITE)

            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        })

        sp_time.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                (parent.getChildAt(0) as TextView).setTextColor(Color.WHITE)

            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        })

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment DetailsFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            DetailsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    fun initView(view: View) {
            rv_machdata = view.findViewById<RecyclerView>(R.id.rv_machdata)
            detailsAdapter = DetailsAdapter(detailsList)
            val layoutManager = GridLayoutManager(context, 2)
            rv_machdata.layoutManager = layoutManager
            rv_machdata.itemAnimator = DefaultItemAnimator()
            rv_machdata.adapter = detailsAdapter


}

    private fun prepareDetailsData() {
        var data = DetailsModel("Set Temp", TemperatureMeasurementStr, "98(99)", "75(76)", "30%")
        detailsList.add(data)
        data = DetailsModel("M/C Temp", TemperatureMeasurementStr1, "98(99)", "78(77)", "30%")
        detailsList.add(data)
        data = DetailsModel("Set Rh", "10%", "98(99)", "79(77)", "30%")
        detailsList.add(data)
        data = DetailsModel("M/C Rh", "10%", "98(99)", "95(77)", "30%")
        detailsList.add(data)
        data = DetailsModel("Fan", "10%", "98(99)", "95(77)", "30%")
        detailsList.add(data)
        data = DetailsModel("Heater", "10%", "98(99)", "95(77)", "30%")
        detailsList.add(data)
        data = DetailsModel("CO2", "412 PPM", "98(99)", "95(77)", "30%")
        detailsList.add(data)
        data = DetailsModel("Damper", "Open", "98(99)", "95(77)", "30%")
        detailsList.add(data)
        data = DetailsModel("Cooling Coil", "10%", "98(99)", "95(77)", "30%")
        detailsList.add(data)
        data = DetailsModel("Humidfier", "OFF", "98(99)", "95(77)", "30%")
        detailsList.add(data)
        data = DetailsModel("Power", "ON", "98(99)", "95(77)", "30%")
        detailsList.add(data)
        data = DetailsModel("Door", "CLOSED", "98(99)", "95(77)", "30%")
        detailsList.add(data)
        detailsAdapter.notifyDataSetChanged()
    }
}