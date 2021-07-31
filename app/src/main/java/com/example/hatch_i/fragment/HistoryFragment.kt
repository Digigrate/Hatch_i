package com.example.hatch_i.fragment

import android.app.DatePickerDialog
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.TranslateAnimation
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatSpinner
import androidx.fragment.app.Fragment
import com.example.hatch_i.R
import com.example.hatch_i.common.Utils
import com.example.hatch_i.model.Content
import com.example.hatch_i.model.Score
import com.example.hatch_i.storageHelpers.PreferenceHelper
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.text.DateFormat
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
 * Use the [HistoryFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
var trans: TranslateAnimation? = null
private lateinit var lineChart: LineChart
private lateinit var tv_fromdte: TextView
private lateinit var tv_todte: TextView
private var scoreList = ArrayList<Score>()
val calendar = Calendar.getInstance()
var dataList: ArrayList<Content> = arrayListOf()
var dataListdistinct: ArrayList<Content> = arrayListOf()
var strProductsForYouList = ""
var dev_id: String? = ""
private val cubatorList = ArrayList<Any>()
private val tempList = ArrayList<Any>()
val sIds = ArrayList<Any>()
val outputFormat: DateFormat = SimpleDateFormat("dd MMMM yyyy", Locale.US)
val inputFormat: DateFormat = SimpleDateFormat("dd MMMM yyyy", Locale.US)
val inputFormat1: DateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US)
 lateinit var ll_root_history_view: LinearLayout


class HistoryFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    internal lateinit var view: View
    lateinit var sp_incubator: AppCompatSpinner
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
        view = inflater.inflate(R.layout.fragment_history, container, false)
        (activity as AppCompatActivity).supportActionBar?.title = "History"
        strProductsForYouList =
            PreferenceHelper.getStringPreference(requireContext(), "STR_PRODUCTS_FOR_YOU_LIST")
                .toString()
        println(strProductsForYouList)
        var gson: Gson? = Gson()
        var featuresListType = object : TypeToken<ArrayList<Content?>?>() {}.type
        dataList = gson!!.fromJson(strProductsForYouList, featuresListType)
        initView(view)
        //setDataToLineChart()
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        for (i in 0 until dataList!!.size) {
            //if (dataList!![i].device_id!!.equals(machineId)) {
            dev_id = dataList!![0].device_id!!.toString()
            cubatorList.add(dataList!![i].name.toString())
        }
        //sIds.add(cubatorList.distinctBy { it.de })
        val sp_incub = view.findViewById<View>(R.id.sp_incub) as Spinner

        val aa: ArrayAdapter<*> = ArrayAdapter<Any?>(
            requireContext(), R.layout.custom_spinner,
            cubatorList.distinct()
        )


        //sp_incub.setSelection(aa.getPosition(machineName as Nothing?));
        sp_incub.adapter = aa

        sp_incub.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                (parent.getChildAt(0) as TextView).setTextColor(Color.parseColor("#0071CA"))
                //(parent.getChildAt(0) as TextView).setTypeface(Typeface.DEFAULT_BOLD)
                var machname = parent.selectedItem.toString()
                println("List Distinct" + machname)
                for (i in 0 until dataList!!.size) {
                    if (dataList!![i].name!!.equals(machname)) {
                        dev_id = dataList!![i].device_id.toString()
                        lineChart.clear()
                        getChartData(dev_id!!)
                    }
                }

            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        })

        getChartData(dev_id!!)
    }


    fun getChartData(dev_id: String) {

        val date1 = tv_fromdte.text.toString()
        val date2 = tv_todte.text.toString()
        val from_date: Date = inputFormat.parse(date1)
        val to_date: Date = inputFormat.parse(date2)

        scoreList.clear()
        for (i in 0 until dataList!!.size) {
            if (dataList!![i].device_id.toString().equals(dev_id)) {
                try {
                    val frmdt: Date = inputFormat1.parse(dataList!![i].created_at.toString())
                    println(from_date)
                    println(frmdt)
                    if (from_date.before(frmdt) && to_date.after(frmdt)) {
                        scoreList.add(
                            Score(
                                outputFormat.format(frmdt).toString(),
                                dataList!![i].set_temp!!.toInt()
                            )
                        )
                    }
                    println(tempList)

                } catch (e: ParseException) {
                    e.printStackTrace()
                }

            }
        }
        setDataToLineChart(scoreList)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HistoryFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HistoryFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    fun initView(view: View) {
        lineChart = view.findViewById(R.id.lineChart)
        tv_todte = view.findViewById(R.id.tv_todte)
        tv_fromdte = view.findViewById(R.id.tv_fromdte)
        ll_root_history_view = view.findViewById(R.id.ll_root_history_view)

        val calendar = Calendar.getInstance()
        calendar.add(Calendar.MONTH, -1)
        val date = System.currentTimeMillis()
        val sdf = SimpleDateFormat("dd MMM yyyy")
        val dateString = sdf.format(date)
        tv_todte.setText(dateString)

        val date1 = calendar.time
        val dateOutput = sdf.format(date1)
        tv_fromdte.setText(dateOutput)

        tv_todte.setOnClickListener(View.OnClickListener {
            lineChart.clear()
            val day = calendar[Calendar.DAY_OF_WEEK]
            val month = calendar[Calendar.MONTH]
            val year = calendar[Calendar.YEAR]
            val dpd = DatePickerDialog(
                requireContext(),
                { datePicker, nYear, nMonth, nDay ->
                    val sdf = SimpleDateFormat("dd MMM yyyy")
                    calendar[nYear, nMonth] = nDay
                    val dateString: String = sdf.format(calendar.time)
                    tv_todte.setText(dateString)
                    setChartData()
                }, year, month, day
            )
            dpd.show()
        })

        tv_fromdte.setOnClickListener(View.OnClickListener {
            lineChart.clear()
            val day = calendar[Calendar.DAY_OF_WEEK]
            val month = calendar[Calendar.MONTH]
            val year = calendar[Calendar.YEAR]
            val dpd = DatePickerDialog(
                requireContext(),
                { datePicker, nYear, nMonth, nDay ->
                    val sdf = SimpleDateFormat("dd MMM yyyy")
                    calendar[nYear, nMonth] = nDay
                    val dateString: String = sdf.format(calendar.time)
                    tv_fromdte.setText(dateString)
                }, year, month, day
            )
            dpd.show()
        })


    }

    private fun setChartData() {
        val date1 = tv_fromdte.text.toString()
        val date2 = tv_todte.text.toString()
        val from_date: Date = inputFormat.parse(date1)
        val to_date: Date = inputFormat.parse(date2)

        if(from_date.after(to_date)){
            Utils.showIndefiniteSnackBar(
                ll_root_history_view,
                "From Date Can not be Greater than To Date"
            )
        }else if(to_date.before(from_date)){
            Utils.showIndefiniteSnackBar(
                ll_root_history_view,
                "To Date Can not be Less than From Date"
            )
        }else{
            getChartData(dev_id!!)
        }





    }

    inner class MyAxisFormatter : IndexAxisValueFormatter() {

        override fun getAxisLabel(value: Float, axis: AxisBase?): String {
            //val index = value.toInt()
            return try {
                val index = value.toInt()
                scoreList.get(index).name.toString()
            } catch (e: Exception) {
                ""
            }
//            return if (index < scoreList.size) {
//                scoreList[index].name
//            } else {
//                ""
//            }
        }
    }


    private fun setDataToLineChart(scoreList: ArrayList<Score>) {
        if (scoreList.size != 0) {
            //        hide grid lines
            lineChart.axisLeft.setDrawGridLines(false)
            val xAxis: XAxis = lineChart.xAxis
            xAxis.setDrawGridLines(false)
            lineChart.setGridBackgroundColor(Color.WHITE)
            //remove right y-axis
            lineChart.axisRight.isEnabled = false

            //remove legend
            lineChart.legend.isEnabled = false


            //remove description label
            lineChart.description.isEnabled = false


            //add animation
            lineChart.animateX(1000, Easing.EaseInSine)
            // xAxis.setAxisMinimum(10F);
            // xAxis.setAxisMaximum(100F);
            // to draw label on xAxis
            xAxis.position = XAxis.XAxisPosition.BOTTOM_INSIDE
            xAxis.valueFormatter = MyAxisFormatter()
            xAxis.setDrawLabels(true)
            xAxis.granularity = 1f
            xAxis.labelRotationAngle = +90f
            lineChart.setDrawGridBackground(true);
            lineChart!!.getXAxis().setDrawAxisLine(true)
            lineChart!!.getXAxis().setDrawLabels(true)
            lineChart!!.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
            lineChart.getXAxis().setTextColor(Color.BLUE);
            lineChart.getXAxis().setTextSize(12f);
            lineChart.getXAxis().setTypeface(Typeface.DEFAULT_BOLD);
            //now draw bar chart with dynamic data
            val entries: ArrayList<Entry> = ArrayList()
            for (i in scoreList.indices) {
                val score = scoreList[i]
                entries.add(Entry(i.toFloat(), score.score.toFloat()))
            }

            val lineDataSet = LineDataSet(entries, "Temperature")
            val data = LineData(lineDataSet)
            lineChart!!.data = data

            lineChart!!.invalidate()
        } else {
            lineChart.clear()
        }
    }

}


