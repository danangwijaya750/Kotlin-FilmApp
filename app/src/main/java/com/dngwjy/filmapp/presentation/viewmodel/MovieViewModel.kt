package com.dngwjy.filmapp.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.ViewModel
import com.dngwjy.filmapp.base.IsError
import com.dngwjy.filmapp.base.IsLoading
import com.dngwjy.filmapp.base.LiveDataState
import com.dngwjy.filmapp.base.ShowData
import com.dngwjy.filmapp.data.MovieRepository
import com.dngwjy.filmapp.presentation.model.MovieModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

/**
 * Created by wijaya on 05/07/19
 */
class MovieViewModel():ViewModel(){
    val disposable=CompositeDisposable()
    lateinit var movies:MutableList<MovieModel>
    val liveDataState=MutableLiveData<LiveDataState>()
    val repository: MovieRepository=MovieRepository()
    fun getMovie(){
        if(this::movies.isInitialized){
            liveDataState.value=ShowData(movies)
            return
        }
        movies= mutableListOf()
        disposable.add(
            repository.getData().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { liveDataState.value=IsLoading(true) }
                .doOnComplete { liveDataState.value=IsLoading(false) }
                .subscribe({
                    this.movies.addAll(it)
                    liveDataState.value=ShowData(movies)
                },{
                    liveDataState.value=IsError(it.localizedMessage)
                    Log.e(this::class.java.simpleName,it.localizedMessage)
                    it.stackTrace
                }))
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroy(){
        if(!disposable.isDisposed){
            disposable.dispose()
        }
    }

}