package br.com.nicolas.brasilconsulta.ui.ddd

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.nicolas.brasilconsulta.common.Resource
import br.com.nicolas.brasilconsulta.common.UiState
import br.com.nicolas.brasilconsulta.data.repository.MainRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class DirectViewModel(
    private val repository: MainRepository
) : ViewModel() {

    private val _uiState = MutableLiveData<UiState<List<String>>>()
    val uiState: LiveData<UiState<List<String>>> get() = _uiState

    fun fetchDirect(directCode: String) = viewModelScope.launch {
        repository.fetchDirect(directCode).collect { result ->
            when (result) {
                is Resource.Loading -> {
                    _uiState.value = UiState.Loading()
                }
                is Resource.Success -> {
                    _uiState.value = UiState.Success(result.item)
                }
                is Resource.Error -> {
                    _uiState.value = UiState.Error(result.throwable)
                }
            }
        }
    }
}