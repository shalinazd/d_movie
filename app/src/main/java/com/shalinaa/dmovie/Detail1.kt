package com.shalinaa.dmovie

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.shalinaa.dmovie.model.ResultsItem
import kotlinx.android.synthetic.main.activity_detail1.*
import kotlinx.android.synthetic.main.activity_main.*

class Detail1 : AppCompatActivity() {
    companion object{
        const val EXTRA_MOVIE = "extra_movie"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail1)
        supportActionBar?.hide()
        fb_back_detail.setOnClickListener {
            startActivity(Intent(MainActivity.getLaunchService(this)))
        }
        val movie = intent.getParcelableExtra<ResultsItem>("EXTRA_MOVIE") as ResultsItem
        Glide.with(this).load(movie.posterPath).into(iv_poster)
        Glide.with(this).load(movie.backdropPath).into(iv_backdrop)
        tv_dummy_title_detail.text = movie.title
        tv_dummy_rd_detail.text = movie.releaseDate
        tv_dummy_rating_detail.text = movie.voteCount.toString()
        tv_dummy_synopsis_detail.text = movie.overview
    }
}