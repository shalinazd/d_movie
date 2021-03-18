package com.shalinaa.dmovie

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.shalinaa.dmovie.model.ResultsItem
import kotlinx.android.synthetic.main.activity_movie_item.view.*
import org.jetbrains.anko.intentFor

class MovieAdapter (var context: Context, var listMovie: List<ResultsItem?>?) :
    RecyclerView.Adapter<MovieAdapter.ViewHolder>() {


    inner class ViewHolder (view : View) : RecyclerView.ViewHolder(view){
        fun bind (movie: ResultsItem){
            with(itemView){
                tv_title_item.text = movie.title
                tv_dummy_release.text = movie.releaseDate.toString()
                tv_desc_movie.text = movie.overview
                Glide.with(context).load(movie.posterPath).centerCrop().into(iv_item_movie)
                itemView.setOnClickListener{
                    itemView.context.startActivity(
                        itemView.context.intentFor<Detail1>(
                            "EXTRA_MOVIE" to movie
                        )
                    )
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieAdapter.ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.activity_movie_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = listMovie!!.size


    override fun onBindViewHolder(holder: MovieAdapter.ViewHolder, position: Int) {
        holder.bind(listMovie?.get(position)!!)
    }

}