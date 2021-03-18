package com.shalinaa.dmovie

import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import com.shalinaa.dmovie.model.ResponseMovie
import com.shalinaa.dmovie.service.RetroConfig
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity(), View.OnClickListener {
    var refUsers : DatabaseReference? = null
    var firebaseUser : FirebaseUser? = null
    val date = getCurrentDateTime()
//    val dateNew = Calendar.getInstance()
//    val year = dateNew.get(Calendar.YEAR)
//    val month = dateNew.get(Calendar.MONTH)
//    val day = dateNew.get(Calendar.DAY_OF_MONTH)

    private fun getCurrentDateTime(): Date {
        return Calendar.getInstance().time
    }
    fun Date.toString(format : String, locale : Locale = Locale.getDefault()):String{
        val formatter = SimpleDateFormat (format, locale)
        return formatter.format(this)
    }

    companion object{
        fun getLaunchService (from: Context) = Intent(from, MainActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()
        ib_profile.setOnClickListener(this)
        ib_profile.setOnClickListener(this)
        tv_date.text = date.toString("dd/MM/yyyy")
        getMovie()

    }

    private fun getMovie() {
        val apiKey = "d5fcf53e403ac5c6d8cdf3a654925aac"

        val loading = ProgressDialog.show(this, "Request Data", "Loading. .")
        RetroConfig.getInstance().getMovieData(apiKey).enqueue(
            object : Callback<ResponseMovie>
        {
                override fun onResponse(
                    call: Call<ResponseMovie>,
                    response: Response<ResponseMovie>
                ){
                    Log.d("Response", "Success" + response.body()?.results)
                    loading.dismiss()
                    if (response.isSuccessful) {
                        Log.e("TAG", "onResponse: ${response.body()?.results?.get(0)?.title}" )
                        Toast.makeText(this@MainActivity, "Data sucess !", Toast.LENGTH_SHORT)
                            .show()
                        val movieData = response.body()?.results
                        val movieAdapter = MovieAdapter(this@MainActivity, movieData)
                        rv_main.adapter = movieAdapter
                        rv_main.layoutManager = LinearLayoutManager(this@MainActivity)

                        val dataHighlight = response.body()
                        Glide.with(this@MainActivity)
                            .load(dataHighlight?.results?.component1()?.posterPath)
                            .centerCrop().into(iv_main_banner)
                        Glide.with(this@MainActivity)
                            .load(dataHighlight?.results?.component2()?.posterPath)
                            .centerCrop().into(iv_main_banner2)
                        Glide.with(this@MainActivity)
                            .load(dataHighlight?.results?.component3()?.posterPath)
                            .centerCrop().into(iv_main_banner3)

                        tv_highlight.text = dataHighlight?.results?.component1()?.title
                        tv_highlight2.text = dataHighlight?.results?.component2()?.title
                        tv_highlight3.text = dataHighlight?.results?.component3()?.title

                        tv_date_highlight.text = dataHighlight?.results?.component1()?.releaseDate
                        tv_date_highlight2.text = dataHighlight?.results?.component2()?.releaseDate
                        tv_date_highlight3.text = dataHighlight?.results?.component3()?.releaseDate
                    } else {
                        Toast.makeText(this@MainActivity, "Data Failed !", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
                override fun onFailure(call: Call<ResponseMovie>, t: Throwable) {
                    Log.d("Response", "Failed : " + t.localizedMessage)
                    loading.dismiss()
                }

        })   }

    override fun onClick(p0: View) {
        when(p0.id){
            R.id.ib_profile-> startActivity(Intent(Profile.getLaunchService(this)))
        }
    }
}




