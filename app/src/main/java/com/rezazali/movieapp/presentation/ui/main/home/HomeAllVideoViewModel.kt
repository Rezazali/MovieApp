package com.rezazali.movieapp.presentation.ui.main.home

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rezazali.movieapp.domain.models.BaseMovie
import com.rezazali.movieapp.network.ApiClient
import com.rezazali.movieapp.network.IService
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers

class HomeAllVideoViewModel() : ViewModel() {

    var iService : IService = ApiClient.getInstance().create(IService::class.java)

    lateinit var mutableAllVideo: MutableLiveData<BaseMovie>

    fun getAllVideo() :MutableLiveData<BaseMovie>{

        mutableAllVideo = MutableLiveData<BaseMovie>()

        homeWebService()

        return mutableAllVideo
    }

    private fun homeWebService() {
        iService.getAllVideo().subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe( object : Observer<BaseMovie>{
                override fun onSubscribe(d: Disposable) {

                }

                override fun onNext(t: BaseMovie) {
                    mutableAllVideo.value = t
                    Log.d(TAG, "onNext: ".plus(t.baseMovie))
                }

                override fun onError(e: Throwable) {

                }

                override fun onComplete() {

                }

            })
    }
}