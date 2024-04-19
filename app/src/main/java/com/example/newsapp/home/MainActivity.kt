package com.example.newsapp.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.newsapp.R
import com.example.newsapp.databinding.ActivityMainBinding
import com.example.newsapp.home.newsSources.NewsSourcesFragment

class MainActivity : AppCompatActivity()  {
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

    private var onSearchClickListener : OnSearchClickListener?= null

    fun setOnSearchClickListener(listener: OnSearchClickListener){
        onSearchClickListener = listener

    }
    fun interface OnSearchClickListener{
        fun onSearchClick(query:String)
    }


}