package com.example.hatch_i.fragment

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TableRow
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.hatch_i.R
import com.example.hatch_i.adapter.DataListAdapter
import com.example.hatch_i.common.Utils
import com.example.hatch_i.model.DataMachine


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
private lateinit var datalistAdapter: DataListAdapter
lateinit var btn_adddetails: AppCompatButton
lateinit var rv_detailsdata: RecyclerView
lateinit var ll_dataform: LinearLayout
lateinit var btn_submitdetails: AppCompatButton
lateinit var ll_subdata: LinearLayout
lateinit var tbrow: TableRow


lateinit var et_nuofchicks: EditText
lateinit var et_flockno: EditText
lateinit var et_dateofplacement: EditText
lateinit var et_pno: EditText


private val machineList = ArrayList<DataMachine>()


class DetailsListFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    internal lateinit var view: View

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
        view = inflater.inflate(R.layout.fragment_details_list, container, false)
        (activity as AppCompatActivity).supportActionBar?.title = "Details"
        initView(view)
        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment DetailsListFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            DetailsListFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    fun initView(view: View) {
        btn_submitdetails = view.findViewById(R.id.btn_submitdetails)
        btn_adddetails = view.findViewById(R.id.btn_adddetails)
        rv_detailsdata = view.findViewById(R.id.rv_detailsdata)
        ll_dataform = view.findViewById(R.id.ll_dataform)
        ll_subdata = view.findViewById(R.id.ll_subdata)
        et_nuofchicks = view.findViewById(R.id.et_nuofchicks)
        et_flockno = view.findViewById(R.id.et_flockno)
        et_dateofplacement = view.findViewById(R.id.et_dateofplacement)
        et_pno = view.findViewById(R.id.et_pno)
        tbrow = view.findViewById(R.id.tbrow)

        btn_submitdetails.setOnClickListener(View.OnClickListener {

            Utils.showDialog1(
                "Do you want to add subdata?",
                DialogInterface.OnClickListener { dialog, which ->
                    when (which) {
                        DialogInterface.BUTTON_POSITIVE -> {
                            val myLayout = view.findViewById(R.id.ll_dataform) as LinearLayout
                            val newLayout: View =
                                layoutInflater.inflate(R.layout.subdata_details, null, false)
                            myLayout.addView(newLayout)
                            dialog.dismiss()
                        }

                    }
                }, requireContext()
            )

        })


        btn_adddetails.setOnClickListener(View.OnClickListener {

            Utils.showDialog1(
                "Do you want to add Table Row?",
                DialogInterface.OnClickListener { dialog, which ->
                    when (which) {
                        DialogInterface.BUTTON_POSITIVE -> {
                            val myLayout = view.findViewById(R.id.ll_dataform) as LinearLayout
                            val newLayout: View =
                                layoutInflater.inflate(R.layout.subdata_details_table, null, false)
                            myLayout.addView(newLayout)
                            dialog.dismiss()
                        }

                    }
                }, requireContext()
            )

        })
//        ll_dataform.visibility = View.GONE
//
//        btn_adddetails.setOnClickListener(View.OnClickListener {
//
//            et_title.setText("")
//            et_description.setText("")
//            et_machinnumber.setText("")
//            rv_detailsdata.visibility = View.GONE
//            btn_adddetails.visibility = View.GONE
//            btn_submitdetails.visibility = View.VISIBLE
//            ll_dataform.visibility = View.VISIBLE
//
//        })
//
//
//        btn_submitdetails.setOnClickListener(View.OnClickListener {
//
//            val datalist = DataMachine(et_title.text.toString(), et_description.text.toString(), et_machinnumber.text.toString())
//            machineList.add(datalist)
//
//            rv_detailsdata.visibility = View.VISIBLE
//            btn_adddetails.visibility = View.VISIBLE
//            btn_submitdetails.visibility = View.GONE
//            ll_dataform.visibility = View.GONE
//
//
//            datalistAdapter = DataListAdapter(machineList)
//            val layoutManager = LinearLayoutManager(context)
//            rv_detailsdata.layoutManager = layoutManager
//            rv_detailsdata.itemAnimator = DefaultItemAnimator()
//            rv_detailsdata.adapter = datalistAdapter
//            datalistAdapter.notifyDataSetChanged()
//
//
//
//        })
//
//
//    }
    }
}


