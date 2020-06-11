package com.ibrahim.carstask.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ibrahim.carstask.data.model.CarModel
import com.ibrahim.carstask.data.response.CarResponse
import com.ibrahim.carstask.network.APIClient
import com.ibrahim.carstask.network.NetworkState
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers


class CarViewModel : ViewModel() {


    private val _networkState = MutableLiveData<NetworkState>()
    val networkState: LiveData<NetworkState> = _networkState

    private val _carList = MutableLiveData<List<CarModel>>()
    val carList: LiveData<List<CarModel>> = _carList


    init {
        getCarList()
    }

     fun getCarList() {
        _networkState.postValue(NetworkState.LOADING)

        val compositeDisposable = CompositeDisposable()
        compositeDisposable.add(
            APIClient.build()?.getCarList()
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribeOn(Schedulers.io())
                ?.subscribe({ response -> onResponse(response) }, { t -> onFailure(t) })!!
        )
    } // fun of getCarList


    private fun onFailure(t: Throwable) {

        _networkState.postValue(
            NetworkState(
                NetworkState.Status.FAILED,
                t.message.toString()
            )
        )

    } // fun of onFailure

    private fun onResponse(response: CarResponse) {

        _carList.value = response.vehicles
        _networkState.postValue(NetworkState.LOADED)

    } // fun of onResponse

} // class of CarViewModel