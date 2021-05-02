package com.example.urlshortener.ui.main

import androidx.lifecycle.*
import com.example.urlshortener.data.model.Url
import com.example.urlshortener.data.repository.MainRepository
import com.example.urlshortener.utils.NetworkHelper
import com.example.urlshortener.utils.Resource
import kotlinx.coroutines.launch
import java.util.*
import kotlin.collections.HashMap

class MainViewModel(
    private val mainRepository: MainRepository,
    private val networkHelper: NetworkHelper
) :ViewModel() {
    lateinit var text: String
    private val _url = MutableLiveData<Resource<Url>>()
    val url: LiveData<Resource<Url>>
        get() = _url
    init {
        fetchUrl()
    }

    private fun fetchUrl() {
        viewModelScope.launch{
            _url.postValue(Resource.loading(null))
            if (networkHelper.isNetworkConnected()) {
                val map= HashMap<String, String>()
                map.put("url", text)
                mainRepository.encurtaUrl(map).let{
                    if (it.isSuccessful) {
                        _url.postValue(Resource.success(it.body()))
                    } else _url.postValue(Resource.error("No internet connection", null))
                }
            }
        }
    }

}