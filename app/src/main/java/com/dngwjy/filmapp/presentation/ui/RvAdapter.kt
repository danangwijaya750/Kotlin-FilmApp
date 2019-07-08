package com.dngwjy.filmapp.presentation.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dngwjy.filmapp.R
import com.dngwjy.filmapp.presentation.model.MovieModel
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_list.*

/**
 * Created by wijaya on 05/07/19
 */
class RvAdapter(val data:List<MovieModel>,val listener:(MovieModel)->Unit)
    :RecyclerView.Adapter<RvAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
            : ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.item_list,parent,false))
    }

    override fun getItemCount(): Int =data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(data[position],listener)
    }

    class ViewHolder(override val containerView: View)
        :RecyclerView.ViewHolder(containerView),LayoutContainer {

        fun bindData(movie:MovieModel,listen: (MovieModel) -> Unit){
            Glide.with(containerView).load("https://image.tmdb.org/t/p/w780${movie.posterPath}")
                .into(img_photo)
            txt_name.text=movie.title
            txt_description.text=movie.overview

            itemView.setOnClickListener { listen(movie) }
        }
    }
}