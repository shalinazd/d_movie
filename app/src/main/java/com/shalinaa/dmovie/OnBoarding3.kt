package com.shalinaa.dmovie

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_on_boarding3.*

class OnBoarding3 : AppCompatActivity(), View.OnClickListener {
    companion object{
        fun getLaunchService (from: Context) = Intent(from, OnBoarding3::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_on_boarding3)
        supportActionBar?.hide()
        tv_start_ob3.setOnClickListener(this)
    }
    override fun onClick(p0: View) {
        when(p0.id){
            R.id.tv_start_ob3 -> startActivity(Intent(Login.getLaunchService(this)))
        }
    }
}