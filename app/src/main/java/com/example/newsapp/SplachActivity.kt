package com.example.newsapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class SplachActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splach_actvity)
        startHomeActivity()
    }

    private fun startHomeActivity() {
        val handler = Handler()
        handler.postDelayed({
            val intent: Intent = Intent(this@SplachActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }, 1500)
    }
}