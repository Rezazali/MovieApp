package com.rezazali.movieapp.presentation.ui.main.home

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rezazali.movieapp.domain.models.BaseMovie
import com.rezazali.movieapp.domain.utils.IdCategory
import com.rezazali.movieapp.network.ApiClient
import com.rezazali.movieapp.network.IService
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers

class HomeCategoryViewModel() : ViewModel() {

    var iService : IService = ApiClient.getInstance().create(IService::class.java)

    lateinit var mutableDramaCategory : MutableLiveData<BaseMovie>

    lateinit var mutableScaryCategory : MutableLiveData<BaseMovie>

    fun getCategoryDrama(id : Int) : MutableLiveData<BaseMovie>{
        mutableDramaCategory = MutableLiveData<BaseMovie>()

        homeWebService(id)

        return mutableDramaCategory
    }


    fun getCategoryScary(id : Int) : MutableLiveData<BaseMovie>{

        mutableScaryCategory = MutableLiveData<BaseMovie>()

        homeWebService(id)

        return mutableScaryCategory
    }

    private fun homeWebService(id: Int) {
        

        iService.getVideoByCategoryId(id).subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe( object : Observer<BaseMovie> {
                override fun onSubscribe(d: Disposable) {

                }

                override fun onNext(t: BaseMovie) {
                    when(id){
                        IdCategory.DRAMA.idWeb -> {
                            mutableDramaCategory.value = t
                        }

                        IdCategory.SCARY.idWeb ->{
                            mutableScaryCategory.value = t
                        }
                    }
                }

                override fun onError(e: Throwable) {
                    Log.d(TAG, "onError: ")
                }

                override fun onComplete() {

                }


            })

    }
}