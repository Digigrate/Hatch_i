package com.example.garinnoglobal.fragment

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.annotation.Nullable
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.hatch_i.R
import com.example.hatch_i.model.Content
import com.example.hatch_i.storageHelpers.PreferenceHelper
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


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
    internal lateinit var view: View
    var machineId: String? = ""
    var machineName: String? = "aa"
    var selectedId: Int? = 0
    var selectedId1: Int? = 0
    private val cubatorList = ArrayList<Any>()
    val sIds = ArrayList<Any>()
    private val timediffrentList = ArrayList<Any>()
    private val subDataList = ArrayList<Content>()
    var dataList: ArrayList<Content> = arrayListOf()
    var dataListdistinct: ArrayList<Content> = arrayListOf()
    var strProductsForYouList = ""
    lateinit var progressBar: ProgressBar
    lateinit var img0: ImageView
    lateinit var img1: ImageView
    lateinit var img2: ImageView
    lateinit var img3: ImageView
    lateinit var img4: ImageView
    lateinit var img5: ImageView
    lateinit var img6: ImageView
    lateinit var img7: ImageView
    lateinit var img8: ImageView
    lateinit var img9: ImageView
    lateinit var img10: ImageView
    lateinit var img11: ImageView

    lateinit var tv_data0: TextView
    lateinit var tv_data1: TextView
    lateinit var tv_data2: TextView
    lateinit var tv_data3: TextView
    lateinit var tv_data4: TextView
    lateinit var tv_data5: TextView
    lateinit var tv_data6: TextView
    lateinit var tv_data7: TextView
    lateinit var tv_data8: TextView
    lateinit var tv_data9: TextView
    lateinit var tv_data10: TextView
    lateinit var tv_data11: TextView

    lateinit var tv_subdata0: TextView
    lateinit var tv_subdata1: TextView
    lateinit var tv_subdata2: TextView
    lateinit var tv_subdata3: TextView
    lateinit var tv_subdata4: TextView
    lateinit var tv_subdata5: TextView
    lateinit var tv_subdata6: TextView
    lateinit var tv_subdata7: TextView
    lateinit var tv_subdata8: TextView
    lateinit var tv_subdata9: TextView
    lateinit var tv_subdata10: TextView
    lateinit var tv_subdata11: TextView
    lateinit var datemach: TextView
    lateinit var timemach: TextView
    var one_click: Boolean = false

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
        (activity as AppCompatActivity).supportActionBar?.title = "Incubation Details"
        machineId = PreferenceHelper.getStringPreference(context, "machineId")
        machineName = PreferenceHelper.getStringPreference(context, "machinename")
        strProductsForYouList =
            PreferenceHelper.getStringPreference(requireContext(), "STR_PRODUCTS_FOR_YOU_LIST")
                .toString()
        println(strProductsForYouList)
        var gson: Gson? = Gson()
        var featuresListType = object : TypeToken<ArrayList<Content?>?>() {}.type
        dataList = gson!!.fromJson(strProductsForYouList, featuresListType)
        getDropDownOfIncubator()
        return view
    }

    private fun getDropDownOfIncubator() {
        for (i in 0 until dataList!!.size) {
            if (dataList!![i].device_id!!.equals(machineId)) {
                cubatorList.add(dataList!![i].name.toString())
            }
        }
        timediffrentList.clear()
        for (i in 0 until dataList!!.size) {
            if (dataList!![i].device_id!!.equals(machineId)) {
                val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                try {
                    val date1 = simpleDateFormat.parse(dataList!![i].created_at.toString())
                    val c = Calendar.getInstance().time
                    println("Current time => $c")
                    val df = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
                    val formattedDate = df.format(c)
                    val date2 = simpleDateFormat.parse(formattedDate.toString())
                    printDifference(date1, date2,dataList!![i].id)
                } catch (e: ParseException) {
                    e.printStackTrace()
                }

            }
        }

    }

    fun printDifference(startDate: Date, endDate: Date, dev_id: Int?) {
        //
        //milliseconds
        var different = endDate.time - startDate.time
        println("startDate : $startDate")
        println("endDate : $endDate")
        println("different : $different")
        val secondsInMilli: Long = 1000
        val minutesInMilli = secondsInMilli * 60
        val hoursInMilli = minutesInMilli * 60
        val daysInMilli = hoursInMilli * 24
        val elapsedDays = different / daysInMilli
        different = different % daysInMilli
        val elapsedHours = different / hoursInMilli
        different = different % hoursInMilli
        val elapsedMinutes = different / minutesInMilli
        different = different % minutesInMilli
        val elapsedSeconds = different / secondsInMilli
        System.out.printf(
            "%d days, %d hours, %d minutes, %d seconds%n",
            elapsedDays, elapsedHours, elapsedMinutes, elapsedSeconds
        )
        var timediff =
            elapsedDays.toString() + "d " + elapsedHours.toString() + "h " + elapsedMinutes.toString() + "m"
        timediffrentList.add(timediff)
        sIds.add(dev_id!!)

    }


    override fun onViewCreated(view: View, @Nullable savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sp_time = view.findViewById<View>(R.id.sp_time) as Spinner
        val sp_incub = view.findViewById<View>(R.id.sp_incub) as Spinner

        val aa: ArrayAdapter<*> = ArrayAdapter<Any?>(
            requireContext(), R.layout.custom_spinner,
            cubatorList.distinct()
        )


        //sp_incub.setSelection(aa.getPosition(machineName as Nothing?));
        sp_incub.adapter = aa


        val aa1: ArrayAdapter<*> = ArrayAdapter<Any?>(
            requireContext(), R.layout.custom_spinner,
            timediffrentList as List<Any?>
        )
        sp_time.adapter = aa1


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

//

        sp_time.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                (parent.getChildAt(0) as TextView).setTextColor(Color.WHITE)
                println("List Distinct"+sIds.get(position).toString())
                println("List Distinct"+sIds.get(position).toString())
                progressBar.visibility = View.VISIBLE
                getSubDataValues(sIds.get(position).toString())
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
        progressBar = view.findViewById(R.id.progressBar)
        datemach = view.findViewById(R.id.datemach)
        timemach = view.findViewById(R.id.timemach)
        val c = Calendar.getInstance().time
        println("Current time => $c")
        val df = SimpleDateFormat("EEEE dd MMMM yyyy", Locale.getDefault())
        val formattedDate = df.format(c)
        datemach.setText(formattedDate.toString())
        val df1 = SimpleDateFormat(" hh:mm a", Locale.getDefault())
        val formattedDate1 = df1.format(c)
        timemach.setText(formattedDate1.toString())

        img0 = view.findViewById(R.id.img0)
        img1 = view.findViewById(R.id.img1)
        img2 = view.findViewById(R.id.img2)
        img3 = view.findViewById(R.id.img3)
        img4 = view.findViewById(R.id.img4)
        img5 = view.findViewById(R.id.img5)
        img6 = view.findViewById(R.id.img6)
        img7 = view.findViewById(R.id.img7)
        img8 = view.findViewById(R.id.img8)
        img9 = view.findViewById(R.id.img9)
        img10 = view.findViewById(R.id.img10)
        img11 = view.findViewById(R.id.img11)

        tv_data0 = view.findViewById(R.id.tv_data0)
        tv_data1 = view.findViewById(R.id.tv_data1)
        tv_data2 = view.findViewById(R.id.tv_data2)
        tv_data3 = view.findViewById(R.id.tv_data3)
        tv_data4 = view.findViewById(R.id.tv_data4)
        tv_data5 = view.findViewById(R.id.tv_data5)
        tv_data6 = view.findViewById(R.id.tv_data6)
        tv_data7 = view.findViewById(R.id.tv_data7)
        tv_data8 = view.findViewById(R.id.tv_data8)
        tv_data9 = view.findViewById(R.id.tv_data9)
        tv_data10 = view.findViewById(R.id.tv_data10)
        tv_data11 = view.findViewById(R.id.tv_data11)

        tv_subdata0 = view.findViewById(R.id.tv_subdata0)
        tv_subdata1 = view.findViewById(R.id.tv_subdata1)
        tv_subdata2 = view.findViewById(R.id.tv_subdata2)
        tv_subdata3 = view.findViewById(R.id.tv_subdata3)
        tv_subdata4 = view.findViewById(R.id.tv_subdata4)
        tv_subdata5 = view.findViewById(R.id.tv_subdata5)
        tv_subdata6 = view.findViewById(R.id.tv_subdata6)
        tv_subdata7 = view.findViewById(R.id.tv_subdata7)
        tv_subdata8 = view.findViewById(R.id.tv_subdata8)
        tv_subdata9 = view.findViewById(R.id.tv_subdata9)
        tv_subdata10 = view.findViewById(R.id.tv_subdata10)
        tv_subdata11 = view.findViewById(R.id.tv_subdata11)

    }

    private fun getSubDataValues(seletedvalue: String) {
        subDataList.clear()
        for (i in 0 until dataList!!.size) {
                if (dataList[i].id!!.toString().equals(seletedvalue)) {
                    subDataList.add(dataList!![i])
                }
            }

        tv_subdata0.setText(subDataList[0].set_temp + "\u2109")
        tv_subdata1.setText(subDataList[0].machine_temp + "\u2109")
        tv_subdata2.setText(subDataList[0].set_humidity)
        tv_subdata3.setText(subDataList[0].machine_humidity)
        tv_subdata4.setText(subDataList[0].fan)
        tv_subdata5.setText(subDataList[0].heater)
        tv_subdata6.setText(subDataList[0].co2)
        tv_subdata7.setText(subDataList[0].damper)
        tv_subdata8.setText(subDataList[0].cooling_coil)
        if (subDataList[0].humidifier.equals("1")) {
            tv_subdata9.setText("ON")
        } else {
            tv_subdata9.setText("OFF")
        }
        if (subDataList[0].power.equals("1")) {
            tv_subdata10.setText("ON")
        } else {
            tv_subdata10.setText("OFF")
        }
        tv_subdata11.setText(subDataList[0].door)
        tv_data0.setText("Set Temp")
        img0.setImageResource(R.drawable.tempnor)
        tv_subdata0.setTextColor(Color.parseColor("#D73729"))
        tv_data1.setText("M/C Temp")
        img1.setImageResource(R.drawable.tempnor)
        tv_subdata1.setTextColor(Color.parseColor("#D73729"))
        tv_data2.setText("Set Rh")
        img2.setImageResource(R.drawable.drop)
        tv_subdata2.setTextColor(Color.parseColor("#D73729"))
        tv_data3.setText("M/C Rh")
        img3.setImageResource(R.drawable.drop)
        tv_subdata3.setTextColor(Color.parseColor("#D73729"))
        tv_data4.setText("Fan")
        img4.setImageResource(R.drawable.fan)
        tv_subdata4.setTextColor(Color.parseColor("#D73729"))
        tv_data5.setText("Heater")
        img5.setImageResource(R.drawable.heater)
        tv_subdata5.setTextColor(Color.parseColor("#D73729"))
        tv_data6.setText("CO2")
        img6.setImageResource(R.drawable.co2)
        tv_subdata6.setTextColor(Color.parseColor("#D73729"))
        tv_data7.setText("Damper")
        img7.setImageResource(R.drawable.dumper)
        tv_subdata7.setTextColor(Color.parseColor("#D73729"))
        tv_data8.setText("Cooling Coil")
        img8.setImageResource(R.drawable.coil)
        tv_subdata8.setTextColor(Color.parseColor("#D73729"))
        tv_data9.setText("Humidfier")
        img9.setImageResource(R.drawable.humid)
        tv_subdata9.setTextColor(Color.parseColor("#FFFFFFFF"))
        //  tv_subdata9.setBackgroundColor(Color.parseColor("#FF274F"))
        tv_data10.setText("Power")
        img10.setImageResource(R.drawable.powwer)
        tv_subdata10.setTextColor(Color.parseColor("#FFFFFFFF"))
        // tv_subdata10.setBackgroundColor(Color.parseColor("#03DAC6"))
        tv_data11.setText("Door")
        img11.setImageResource(R.drawable.door)
        tv_subdata11.setTextColor(Color.parseColor("#356EAE"))
        progressBar.visibility = View.GONE
    }


}


