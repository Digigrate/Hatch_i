package com.example.hatch_i.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hatch_i.R
import com.example.hatch_i.adapter.NotificationAdapter
import com.example.hatch_i.adapter.NotificationModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [NotificationFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class NotificationFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    lateinit var rv_notification: RecyclerView
    internal lateinit var view: View
    private val notificationsList = ArrayList<NotificationModel>()
    private lateinit var notificationAdapter: NotificationAdapter
    private var TemperatureMeasurementStr: String? =null
    private var TemperatureMeasurementStr1: String? =null
    private var TemperatureMeasurementStr2: String? =null
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
        view = inflater.inflate(R.layout.fragment_notification, container, false)
        TemperatureMeasurementStr = "30" + "\u2103"
        TemperatureMeasurementStr1 = "20" + "\u2103"
        TemperatureMeasurementStr2 = "90" + "\u2103"
        (activity as AppCompatActivity).supportActionBar?.title = "Notification"
        initView(view)
        prepareNotificationData()
        return view

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment NotificationFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            NotificationFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    fun initView(view: View) {


        rv_notification = view.findViewById<RecyclerView>(R.id.rv_notification)
        notificationAdapter = NotificationAdapter(notificationsList)
        val layoutManager = LinearLayoutManager(context)
        rv_notification.layoutManager = layoutManager
        rv_notification.itemAnimator = DefaultItemAnimator()
        rv_notification.adapter = notificationAdapter

    }


    private fun prepareNotificationData() {
        var data = NotificationModel("InCubator1", "Inside Temperature",TemperatureMeasurementStr, "25/05/2021", "08.00PM")
        notificationsList.add(data)
        data = NotificationModel("InCubator1", "Inside Temperature",TemperatureMeasurementStr1, "25/05/2021", "08.00PM")
        notificationsList.add(data)
        data = NotificationModel("InCubator1", "Inside Temperature",TemperatureMeasurementStr2, "25/05/2021", "08.00PM")
        notificationsList.add(data)
        notificationAdapter.notifyDataSetChanged()
    }
}