package com.shalinaa.dmovie

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_profile.*

class Profile : AppCompatActivity(), View.OnClickListener {
    var refUsers : DatabaseReference? = null
    var firebaseUser : FirebaseUser? = null
    companion object{
        fun getLaunchService (from: Context) = Intent(from, Profile::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        supportActionBar?.hide()
        iv_back_profile.setOnClickListener(this)
        tv_log_out.setOnClickListener(this)


    }
    override fun onClick(p0: View) {
        when(p0.id){
            R.id.tv_log_out -> logOut()
            R.id.iv_back_profile -> startActivity(Intent(MainActivity.getLaunchService((this))))
        }
    }

    private fun logOut() {
        startActivity(Intent(
            Login.getLaunchService(
                this
            )
        ))
        FirebaseAuth.getInstance().signOut()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(MainActivity.getLaunchService(this)))
    }
}