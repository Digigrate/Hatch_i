package com.example.hatch_i.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.RelativeLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hatch_i.R
import com.example.hatch_i.adapter.HomeAdapter
import com.example.hatch_i.apiclient.ApiInterface
import com.example.hatch_i.common.Utils
import com.example.hatch_i.model.Content
import com.example.hatch_i.model.HomePageMaster
import com.example.hatch_i.storageHelpers.PreferenceHelper
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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
    lateinit var ll_root_home_view : FrameLayout
    //private val dataList = ArrayList<DataModel>()
    private lateinit var homeAdapter: HomeAdapter
    lateinit var progressBar: ProgressBar
    lateinit var content: ArrayList<Content>
    var status:String? = null

    var dataList: ArrayList<Content> = arrayListOf()
    var dataListdistinct: ArrayList<Content> = arrayListOf()

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
        ll_root_home_view = view.findViewById<FrameLayout>(R.id.ll_root_home_view)
        initView(view)
        getHomePageDataAPI()
        return view
    }

    private fun getHomePageDataAPI() {
        progressBar.visibility=View.VISIBLE
        try {
            if (Utils.isConnectingToInternet(requireContext())) {
                val retIn = ApiInterface.RetrofitInstance.getRetrofitInstance()
                    .create(ApiInterface::class.java)
                retIn.getHomePageData().enqueue(object : Callback<HomePageMaster> {
                    override fun onFailure(call: Call<HomePageMaster>, t: Throwable) {

                    }

                    override fun onResponse(
                        call: Call<HomePageMaster>,
                        response: Response<HomePageMaster>
                    ) {
                        if (response.code() == 200) {
                            progressBar.visibility=View.VISIBLE
                            val responseBody: HomePageMaster? = response.body()
                            if (responseBody != null) {
                                status = responseBody.status.toString()
                                content = (responseBody.content as ArrayList<Content>?)!!
                                println(content)
                                println(status)
                                prepareHomeData(content!!)
                                val productsForYouList = content?: listOf()
                                val strProductsForYouList = Gson().toJson(productsForYouList)
                                PreferenceHelper.setStringPreference(requireContext(),"STR_PRODUCTS_FOR_YOU_LIST",strProductsForYouList)
                                println(productsForYouList)
                            }
                        }else{
                            progressBar.visibility=View.GONE
                            Utils.showIndefiniteSnackBar(
                                ll_root_home_view,
                                response.message()
                            )
                        }
                    }
                })
            }else{
                progressBar.visibility=View.GONE
                Utils.showIndefiniteSnackBar(
                    ll_root_home_view,
                    "You're offline, Please check your network connection."
                )
            }
        } catch (err: Exception) {
            err.printStackTrace()
        }

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
        progressBar = view.findViewById(R.id.progressBar)
        rv_data = view.findViewById<RecyclerView>(R.id.rv_data)


    }



    private fun prepareHomeData(content: ArrayList<Content>) {

            for (i in 0 until content!!.size) {
                dataList.add(content!![i])
            }
            dataListdistinct = dataList.distinctBy { it.device_id } as ArrayList<Content>
            println("List Distinct"+dataList.distinctBy { it.device_id }.toString())
            homeAdapter = HomeAdapter(dataListdistinct)
            val layoutManager = LinearLayoutManager(context)
            rv_data.layoutManager = layoutManager
            rv_data.itemAnimator = DefaultItemAnimator()
            rv_data.adapter = homeAdapter
        homeAdapter.notifyDataSetChanged()
        progressBar.visibility=View.GONE
    }




}