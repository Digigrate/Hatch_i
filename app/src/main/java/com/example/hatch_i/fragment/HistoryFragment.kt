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
import com.example.hatch_i.model.Score
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
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
        initView(view)
        setDataToLineChart()
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val sp_incub = view.findViewById<View>(R.id.sp_incub) as Spinner
        sp_incub.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                (parent.getChildAt(0) as TextView).setTextColor(Color.parseColor("#0071CA"))
                (parent.getChildAt(0) as TextView).setTypeface(Typeface.DEFAULT_BOLD)

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

        //        hide grid lines
        lineChart.axisLeft.setDrawGridLines(false)
        val xAxis: XAxis = lineChart.xAxis
        xAxis.setDrawGridLines(false)
        xAxis.setDrawAxisLine(false)

        //remove right y-axis
        lineChart.axisRight.isEnabled = false

        //remove legend
        lineChart.legend.isEnabled = false


        //remove description label
        lineChart.description.isEnabled = false


        //add animation
        lineChart.animateX(1000, Easing.EaseInSine)

        // to draw label on xAxis
        xAxis.position = XAxis.XAxisPosition.BOTTOM_INSIDE
        xAxis.valueFormatter = MyAxisFormatter()
        xAxis.setDrawLabels(true)
        xAxis.granularity = 1f
        xAxis.labelRotationAngle = +90f

        tv_todte.setOnClickListener(View.OnClickListener {
            val day = calendar[Calendar.DAY_OF_WEEK]
            val month = calendar[Calendar.MONTH]
            val year = calendar[Calendar.YEAR]
            val dpd = DatePickerDialog(
                requireContext(),
                { datePicker, nYear, nMonth, nDay ->
                    val sdf = SimpleDateFormat("dd/MMMM/yyyy")
                    calendar[nYear, nMonth] = nDay
                    val dateString: String = sdf.format(calendar.time)
                    tv_todte.setText(dateString)
                }, year, month, day
            )
            dpd.show()
        })

        tv_fromdte.setOnClickListener(View.OnClickListener {
            val day = calendar[Calendar.DAY_OF_WEEK]
            val month = calendar[Calendar.MONTH]
            val year = calendar[Calendar.YEAR]
            val dpd = DatePickerDialog(
                requireContext(),
                { datePicker, nYear, nMonth, nDay ->
                    val sdf = SimpleDateFormat("dd MMMM yyyy")
                    calendar[nYear, nMonth] = nDay
                    val dateString: String = sdf.format(calendar.time)
                    tv_fromdte.setText(dateString)
                }, year, month, day
            )
            dpd.show()
        })

    }

    inner class MyAxisFormatter : IndexAxisValueFormatter() {

        override fun getAxisLabel(value: Float, axis: AxisBase?): String {
            val index = value.toInt()
            return if (index < scoreList.size) {
                scoreList[index].name
            } else {
                ""
            }
        }
    }


    private fun setDataToLineChart() {
        //now draw bar chart with dynamic data
        val entries: ArrayList<Entry> = ArrayList()

        scoreList.clear()
        scoreList = getScoreList()
        //scoreList = getScoreList1()

        //you can replace this data object with  your custom object
        for (i in scoreList.indices) {
            val score = scoreList[i]
            entries.add(Entry(i.toFloat(), score.score.toFloat()))
        }

        val lineDataSet = LineDataSet(entries, "Temperature")

        val data = LineData(lineDataSet)
        lineChart!!.data = data

        lineChart!!.invalidate()
    }

    // simulate api call
    // we are initialising it directly
    private fun getScoreList(): ArrayList<Score> {
        scoreList.add(Score("J", 50))
        scoreList.add(Score("R", 60))
        scoreList.add(Score("S", 65))
        scoreList.add(Score("K", 60))
        scoreList.add(Score("F", 70))
        scoreList.add(Score("S", 75))
        scoreList.add(Score("K", 80))
        scoreList.add(Score("F", 85))

        return scoreList
    }

}


