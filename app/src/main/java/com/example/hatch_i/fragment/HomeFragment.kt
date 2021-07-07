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
import com.example.hatch_i.adapter.DataModel
import com.example.hatch_i.adapter.HomeAdapter

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    lateinit var rv_data: RecyclerView
    internal lateinit var view: View
    private val dataList = ArrayList<DataModel>()
    private lateinit var homeAdapter: HomeAdapter

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
        view = inflater.inflate(R.layout.fragment_home, container, false)
        (activity as AppCompatActivity).supportActionBar?.title = "Home"
        initView(view)
        prepareHomeData()
        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HomeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    fun initView(view: View) {

        rv_data = view.findViewById<RecyclerView>(R.id.rv_data)
        homeAdapter = HomeAdapter(dataList)
        val layoutManager = LinearLayoutManager(context)
        rv_data.layoutManager = layoutManager
        rv_data.itemAnimator = DefaultItemAnimator()
        rv_data.adapter = homeAdapter

    }



    private fun prepareHomeData() {
        var data = DataModel("01", "10", "98(99)", "75(76)", "30%")
        dataList.add(data)
        data = DataModel("02", "01", "98(99)", "78(77)", "30%")
        dataList.add(data)
        data = DataModel("03", "02", "98(99)", "79(77)", "30%")
        dataList.add(data)
        data = DataModel("04", "03", "98(99)", "95(77)", "30%")
        dataList.add(data)
        homeAdapter.notifyDataSetChanged()
    }
}