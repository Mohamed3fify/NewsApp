package com.example.newsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.newsapp.databinding.ActivityMainBinding
import com.example.newsapp.databinding.FragmentNewsSourcesBinding
import com.example.newsapp.newsSources.NewsSourcesFragment

class MainActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)


        supportFragmentManager
            .beginTransaction()
            .replace(
                R.id.fragment_container,
                NewsSourcesFragment()
            )
            .commit()

    }
}