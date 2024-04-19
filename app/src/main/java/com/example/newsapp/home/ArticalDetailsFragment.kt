package com.example.newsapp.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.newsapp.databinding.FragmentArticalDetailsBinding
import com.example.newsapp.databinding.ItemNewsBinding

class ArticalDetailsFragment:Fragment() {
 lateinit var binding: FragmentArticalDetailsBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentArticalDetailsBinding.inflate(
            inflater , container ,false
        )
        return binding.root
    }
}