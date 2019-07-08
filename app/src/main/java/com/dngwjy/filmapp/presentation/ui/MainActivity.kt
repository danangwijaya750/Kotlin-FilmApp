package com.dngwjy.filmapp.presentation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.dngwjy.filmapp.R
import com.dngwjy.filmapp.base.IsError
import com.dngwjy.filmapp.base.IsLoading
import com.dngwjy.filmapp.base.LiveDataState
import com.dngwjy.filmapp.base.ShowData
import com.dngwjy.filmapp.data.MovieRepository
import com.dngwjy.filmapp.presentation.model.MovieModel
import com.dngwjy.filmapp.presentation.viewmodel.MovieViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(),Observer<LiveDataState> {

    override fun onChanged(t: LiveDataState?) {
        when(t){
            is IsLoading-> {
                Toast.makeText(this, "Loading ${t.state}",
                    Toast.LENGTH_SHORT)
                    .show()
            }
            is ShowData->{
                Log.d(this::class.java.simpleName,t.data.size.toString())
                dataMovie.clear()
                dataMovie.addAll(t.data)
                adapter.notifyDataSetChanged()
            }
            is IsError->{
                Toast.makeText(this,"Error ${t.msg}",
                    Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    private val dataMovie= mutableListOf<MovieModel>()
    lateinit var adapter: RvAdapter
    val viewModel: MovieViewModel=MovieViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel.liveDataState.observe(this,this)

        adapter= RvAdapter(dataMovie){
            Toast.makeText(this,it.title,Toast.LENGTH_SHORT).show()
        }

        recyclerView.apply {
            adapter=this@MainActivity.adapter
            layoutManager=LinearLayoutManager(this@MainActivity)
        }

        viewModel.getMovie()

    }

}
