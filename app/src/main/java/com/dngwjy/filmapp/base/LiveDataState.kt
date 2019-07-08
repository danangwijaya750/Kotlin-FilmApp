package com.dngwjy.filmapp.base

import com.dngwjy.filmapp.presentation.model.MovieModel

/**
 * Created by wijaya on 05/07/19
 */
sealed class LiveDataState

data class IsLoading(val state:Boolean) :LiveDataState()
data class IsError(val msg:String) :LiveDataState()
data class ShowData(val data:List<MovieModel>):LiveDataState()
