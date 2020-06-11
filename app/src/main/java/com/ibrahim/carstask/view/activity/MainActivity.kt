package com.ibrahim.carstask.view.activity

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout.OnRefreshListener
import com.ibrahim.carstask.R
import com.ibrahim.carstask.adapter.CarAdapter
import com.ibrahim.carstask.click.CarClick
import com.ibrahim.carstask.data.model.CarModel
import com.ibrahim.carstask.network.NetworkState
import com.ibrahim.carstask.utils.ProgressLoading
import com.ibrahim.carstask.viewmodel.CarViewModel
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() , CarClick {

    private lateinit var carViewModel: CarViewModel

    private lateinit var adapter : CarAdapter
    private var carList : List<CarModel> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()
        initViewModel()
    } // fun of onCreate


    private fun initView()
    {
        adapter = CarAdapter(this, carList, this)
        recyclerView_cars.layoutManager = LinearLayoutManager(this)
        recyclerView_cars.setHasFixedSize(true)
        recyclerView_cars.adapter = adapter



        swipe_refresh.setOnRefreshListener { makeSwipe() }
    } // fun of initView

    private fun makeSwipe() {
        carViewModel.getCarList()
        swipe_refresh.isRefreshing = false
    } // fun of makeSwipe

    private fun initViewModel()
    {
        carViewModel = ViewModelProviders.of(this).get(CarViewModel::class.java)


        carViewModel.networkState.observe(this,networkStateObserver)
        carViewModel.carList.observe(this,carListObserver)
    } // fun of initViewModel

    private val networkStateObserver = Observer<NetworkState>
    {
        when(it)
        {
            NetworkState.LOADING -> {
                ProgressLoading.show(this)
            } // LOADING
            NetworkState.LOADED -> {
                ProgressLoading.dismiss()
            } // LOADED
            else -> {
                ProgressLoading.dismiss()
                Toast.makeText(this,it.msg,Toast.LENGTH_LONG).show()
            } // FAILED
        }
    } // networkStateObserver

    private val carListObserver = Observer<List<CarModel>>
    {
        carList = it
        adapter.setCarList(carList)
        adapter.notifyDataSetChanged()
    } // carListObserver


    override fun onClick(position: Int) {
        // handle car Click item

    } // fun of carClick
} // class of MainActivity
