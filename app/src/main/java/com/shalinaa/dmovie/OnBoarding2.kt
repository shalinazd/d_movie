package com.shalinaa.dmovie

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_on_boarding1.*
import kotlinx.android.synthetic.main.activity_on_boarding2.*

class OnBoarding2 : AppCompatActivity(), View.OnClickListener {
    companion object{
        fun getLaunchService (from: Context) = Intent(from, OnBoarding2::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_on_boarding2)
        supportActionBar?.hide()

        tv_skip_ob2.setOnClickListener(this)
        tv_next_ob2.setOnClickListener(this)
    }

    override fun onClick(p0: View) {
        when(p0.id){
            R.id.tv_next_ob2 -> startActivity(Intent(OnBoarding3.getLaunchService(this)))
            R.id.tv_skip_ob2 -> startActivity(Intent(OnBoarding3.getLaunchService(this)))
        }
    }
}