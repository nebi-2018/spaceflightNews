package com.example.spaceflightnews.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.spaceflightnews.adapter.NewsAdapter
import com.example.spaceflightnews.databinding.ActivityNewsBinding
import com.example.common.util.Resource
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class NewsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNewsBinding
    private val viewModel: NewsViewModel by viewModels()
    private lateinit var newsAdapter: NewsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        recyclerViewSetUp()

        binding.getNewsButton.setOnClickListener {
            viewModel.getNews()
        }

        viewModel.news.observe(this@NewsActivity) { result ->
            if (!result.data.isNullOrEmpty()) {
                binding.getNewsButton.visibility = View.GONE
                binding.guiderTextView.visibility = View.GONE
            }
            newsAdapter.differ.submitList(result.data)
            binding.paginationProgressBar.isVisible = result is Resource.Loading<*> &&
                    result.data.isNullOrEmpty()
        }
    }

    private fun recyclerViewSetUp() {
        newsAdapter = NewsAdapter()
        binding.rvBreakNews.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(this@NewsActivity)
        }
    }

}
