package com.shalinaa.dmovie

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_on_boarding1.*

class OnBoarding1 : AppCompatActivity(), View.OnClickListener {
    companion object{
        fun getLaunchService (from: Context) = Intent(from, OnBoarding1::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_on_boarding1)
        supportActionBar?.hide()

        tv_skip_ob1.setOnClickListener(this)
        tv_next_ob1.setOnClickListener(this)
    }

    override fun onClick(p0: View) {
        when(p0.id){
            R.id.tv_skip_ob1 -> startActivity(Intent(OnBoarding3.getLaunchService(this)))
            R.id.tv_next_ob1 -> startActivity(Intent(OnBoarding2.getLaunchService(this)))

        }
    }
}