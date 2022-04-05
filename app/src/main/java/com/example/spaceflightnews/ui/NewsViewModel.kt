package com.example.spaceflightnews.ui

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.*
import com.example.common.domain.NewsResponseItem
import com.example.common.util.Resource
import com.example.spaceflightnews.repository.NewsRepository
import com.example.spaceflightnews.repository.NewsRepositoryImp
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val newsRepository: NewsRepository
) : ViewModel() {


    private var newsLiveData = MutableLiveData<Resource<List<NewsResponseItem>>>()
    val news: LiveData<Resource<List<NewsResponseItem>>> = newsLiveData


    fun getNews() {
        viewModelScope.launch {
            try {
                newsRepository.getNews().collect { newsResponse ->
                    newsLiveData.value = newsResponse
                }
            } catch (e: Exception) {
                Log.d("u_error", e.message!!)
            }
        }

    }
}



