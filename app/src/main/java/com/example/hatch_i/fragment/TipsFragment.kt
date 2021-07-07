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
import com.example.hatch_i.adapter.TipsAdapter
import com.example.hatch_i.adapter.TipsModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [TipsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TipsFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    lateinit var rv_tipslayout: RecyclerView
    internal lateinit var view: View
    private val tipsList = ArrayList<TipsModel>()
    private lateinit var tipsAdapter: TipsAdapter

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
        view = inflater.inflate(R.layout.fragment_tips, container, false)
        (activity as AppCompatActivity).supportActionBar?.title = "Tips"
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
         * @return A new instance of fragment TipsFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            TipsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }


    fun initView(view: View) {

        rv_tipslayout = view.findViewById<RecyclerView>(R.id.rv_tipslayout)
        tipsAdapter = TipsAdapter(tipsList)
        val layoutManager = LinearLayoutManager(context)
        rv_tipslayout.layoutManager = layoutManager
        rv_tipslayout.itemAnimator = DefaultItemAnimator()
        rv_tipslayout.adapter = tipsAdapter

    }


    private fun prepareHomeData() {
        var data = TipsModel("General Tips")
        tipsList.add(data)
         data = TipsModel("Egg Candling")
        tipsList.add(data)
         data = TipsModel("Pre Incubation Checklist")
        tipsList.add(data)
          data = TipsModel("Incubation Checklist")
        tipsList.add(data)
         data = TipsModel("Day of Hatching")
        tipsList.add(data)
        data = TipsModel("Post Incubation Checklist")
        tipsList.add(data)
        data = TipsModel("Brooding Guide")
        tipsList.add(data)
        tipsAdapter.notifyDataSetChanged()
    }
}