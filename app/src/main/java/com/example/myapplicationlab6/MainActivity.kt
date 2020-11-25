package com.example.myapplicationlab6

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.provider.Settings
import android.view.Gravity
import android.widget.Button
import android.widget.SeekBar
import android.widget.Toast
import kotlinx.coroutines.*


class MainActivity : AppCompatActivity() {

    var rabprogress = 0
    var turprogress = 0

    var seekBar : SeekBar ?= null
    var seekBar2 : SeekBar ?= null
    var btn_start : Button ?= null
//    val seekBar = findViewById<SeekBar>(R.id.seekBar)
//    val seekBar2 = findViewById<SeekBar>(R.id.seekBar2)
//    val btn_start = findViewById<Button>(R.id.btn_start)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        seekBar = findViewById<SeekBar>(R.id.seekBar)
        seekBar2 = findViewById<SeekBar>(R.id.seekBar2)
        btn_start = findViewById<Button>(R.id.btn_start)
        btn_start!!.setOnClickListener{
            btn_start!!.isEnabled = false

            rabprogress = 0
            turprogress = 0

            seekBar!!.setProgress(0)
            seekBar2!!.setProgress(0)

//            while(rabprogress < 100){
//                rabprogress += (Math.random() * 3).toInt()
//                seekBar!!.setProgress()
//            }
            GlobalScope.launch {
                TaskA()
            }
            GlobalScope.launch {
                TaskB()
            }

        }
    }

    private suspend fun TaskA(){
        while(rabprogress <= 100 && turprogress <= 100){
            delay(100)
            val msg = Message()
            msg.what = 1
            rabprogress += (Math.random() * 3).toInt()
//            seekBar!!.setProgress(rabprogress)
            mhandler.sendMessage(msg)

        }
    }

    private val mhandler = object : Handler(Looper.getMainLooper()){
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            when(msg?.what)
            {
                1 -> seekBar!!.setProgress(rabprogress)
                2 -> seekBar2!!.setProgress(turprogress)
            }
            if(rabprogress >= 100 && turprogress < 100){
                val toast = Toast.makeText(applicationContext , "兔子勝利", Toast.LENGTH_SHORT)
                toast.show()
                btn_start!!.isEnabled = true

            }else if(rabprogress < 100 && turprogress >= 100){
                val toast = Toast.makeText(applicationContext , "兔子勝利", Toast.LENGTH_SHORT)
                toast.show()
                btn_start!!.isEnabled = true
            }
        }
    }

    private suspend fun TaskB(){
        while(rabprogress <= 100 && turprogress <= 100){
            delay(100)
            val msg = Message()
            msg.what = 2
            turprogress += (Math.random() * 3).toInt()
//            seekBar2!!.setProgress(turprogress)
            mhandler.sendMessage(msg)
        }
    }

}

