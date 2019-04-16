package com.example.kotlinokhttp

import android.Manifest
import android.app.DownloadManager
import android.app.VoiceInteractor
import android.content.pm.PackageManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.support.v4.app.ActivityCompat
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.*
import java.io.IOException

const val KotlinLog = "kotlinTest"

class MainActivity : AppCompatActivity() {

    val mHandler = Handler(Handler.Callback {
        when(it.what)
        {
            1 -> txv1.text = it.data.getString("rawData", "raw data null")
            else ->
                Log.d(KotlinLog, "other message")
        }
        true
    })

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn1.setOnClickListener {
            // val req = Request.Builder().url("https://data.taipei/opendata/datalist/apiAccess?scope=resourceAquire&rid=55ec6d6e-dc5c-4268-a725-d04cc262172b").build()
            val req = Request.Builder().url("http://10.3.88.129/~roy/a.html").build()
            val client = OkHttpClient()

            client.newCall(req).enqueue(object : Callback {
                override fun onResponse(call: Call, response: Response) {
                    val string = response.body()!!.string()
                    val msg = Message()
                    val bundle = Bundle()
                    Log.d(KotlinLog, "OkHttp send success $string")
                    msg.what = 1
                    bundle.putString("rawData", string)
                    msg.data = bundle
                    mHandler.sendMessage(msg)
                }

                override fun onFailure(call: Call, e: IOException) {
                    Log.d(KotlinLog, "OkHttp send failure")
                }
            })
        }
    }
}

