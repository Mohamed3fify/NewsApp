package com.example.newsapp.newsSources

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.example.newsapp.R
import com.example.newsapp.api.ApiManager
import com.example.newsapp.api.model.sourcesResponse.Source
import com.example.newsapp.api.model.sourcesResponse.SourcesResponse
import com.example.newsapp.databinding.FragmentNewsSourcesBinding
import com.example.newsapp.newsFragment.NewsFragment
import com.google.android.material.tabs.TabLayout
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsSourcesFragment : Fragment() {
    private lateinit var binding: FragmentNewsSourcesBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding =
            FragmentNewsSourcesBinding.inflate(
                inflater,
                container,
                false,
            )
        return binding.root
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?,
    ) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        getNewsSources()
    }
 val newsFragment = NewsFragment()
    private fun initViews() {
        childFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container , newsFragment)
            .commit()
        binding.tryAgain.setOnClickListener {
         binding.errorView.isVisible = false
            getNewsSources()
        }
    }

    fun changeLoadingVisibility(isLoadingVisible:Boolean){
        binding.progressBar.isVisible = isLoadingVisible

    }

    private fun getNewsSources() {
        changeLoadingVisibility(true)
        ApiManager
            .getServices()
            .getNewsSources()
            .enqueue(object : Callback<SourcesResponse> {
                override fun onResponse(
                    call: Call<SourcesResponse>,
                    response: Response<SourcesResponse>
                ) {
                   //changeLoadingVisibility(false)
                    if (response.isSuccessful) {
                        showNewsSources(response.body()?.sources)
                        return
                    }

                    val responseJson = response.errorBody()?.string()
                    val errorResponse = Gson().fromJson(responseJson, SourcesResponse::class.java)
                    showError(errorResponse.status)
                }

                override fun onFailure(
                    call: Call<SourcesResponse>,
                    t: Throwable)
                {
                    changeLoadingVisibility(false)
                    showError(t.message)
                }


            })
    }

    private fun showNewsSources(sources: List<Source?>?) {
        binding.errorView.isVisible = false
        binding.progressBar.isVisible = false


        sources?.forEach { source->
            val tab = binding.tabLayout.newTab()
            tab.text = source?.name
            tab.tag = source
            binding.tabLayout.addTab(tab)
        }
        binding.tabLayout.addOnTabSelectedListener(object :TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                val source = tab?.tag as Source
                newsFragment.changeSources(source)
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                val source = tab?.tag as Source
                newsFragment.changeSources(source)
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {}

        })
        binding.tabLayout.getTabAt(0)?.select()

    }

    private fun showError(message: String?) {
        binding.errorView.isVisible = true
        binding.errorMessage.text = message
    }
}
