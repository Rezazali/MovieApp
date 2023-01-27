package com.rezazali.movieapp.presentation.ui.main.pager

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rezazali.movieapp.domain.models.BaseBannerMovie
import com.rezazali.movieapp.network.ApiClient
import com.rezazali.movieapp.network.IService
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers

class BannerViewModel() :ViewModel() {

    private var iService : IService = ApiClient.getInstance().create(IService::class.java)

    lateinit var mutableBanner : MutableLiveData<BaseBannerMovie>

    fun getBanner() : MutableLiveData<BaseBannerMovie>{

        mutableBanner = MutableLiveData<BaseBannerMovie>()

        bannerWebService()

        return mutableBanner
    }

    private fun bannerWebService() {

        iService.getBannerMovie().subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe( object : Observer<BaseBannerMovie>{
                override fun onSubscribe(d: Disposable) {

                }

                override fun onNext(t: BaseBannerMovie) {
                    mutableBanner.value = t
                    Log.d(TAG, "onNext: ".plus(t.listBanner))
                }

                override fun onError(e: Throwable) {

                }

                override fun onComplete() {

                }

            })

    }


}