package com.example.kotlinokhttp

import android.Manifest
import android.app.DownloadManager
import android.app.VoiceInteractor
import android.content.pm.PackageManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.*
import java.io.IOException

const val KotlinLog = "kotlinTest"

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn1.setOnClickListener {
            val req = Request.Builder().url("https://data.taipei/opendata/datalist/apiAccess?scope=resourceAquire&rid=55ec6d6e-dc5c-4268-a725-d04cc262172b").build()
            val client = OkHttpClient()

            client.newCall(req).enqueue(object : Callback {
                override fun onResponse(call: Call, response: Response) {
                    val string = response.body()!!.string()
                    Log.d(KotlinLog, "OkHttp send success $string")
                }

                override fun onFailure(call: Call, e: IOException) {
                    Log.d(KotlinLog, "OkHttp send failure")
                }
            })
                /*
                object : Callback{
                override fun onResponse(call: Call, response: Response) {
                    Log.d(KotlinLog, "OkHttp send success")
                }
                override fun onFailure(call: Call, e: IOException?) {
                    Log.d(KotlinLog, "OkHttp send failure")
                }
            })*/
        }
    }
}

