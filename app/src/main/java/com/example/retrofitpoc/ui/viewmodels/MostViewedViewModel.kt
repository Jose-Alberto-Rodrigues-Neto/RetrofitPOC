package com.example.retrofitpoc.ui.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.retrofitpoc.model.NYTMostViewed
import com.example.retrofitpoc.network.RetrofitClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MostViewedViewModel : ViewModel() {
    private val _articles = MutableStateFlow<NYTMostViewed?>(null)
    val articles: StateFlow<NYTMostViewed?> = _articles

    private val _isLoading = MutableStateFlow(false)
    val isLoading : StateFlow<Boolean> = _isLoading

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    fun fetchArticles(){
        viewModelScope.launch { //inicia a corrotina
            _isLoading.value = true //seta is loading como vdd
            try {
                val response = RetrofitClient.instance.getArticles() //utiliza o apiClient para fazer a requisição
                _articles.value = response
                _error.value = null
                Log.i("Ok", "articles get")
            }catch (e: Exception){
                Log.e("Erro", "Erro ${e.message}")
                _error.value = "Error: ${e.message}"
            } finally {
                _isLoading.value = false //seta isLoading como false
            }
        }
    }
}