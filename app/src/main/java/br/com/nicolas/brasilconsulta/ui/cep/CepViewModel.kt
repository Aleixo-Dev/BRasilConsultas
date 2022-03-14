package br.com.nicolas.brasilconsulta.ui.cep

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.nicolas.brasilconsulta.common.Resource
import br.com.nicolas.brasilconsulta.common.UiState
import br.com.nicolas.brasilconsulta.data.datasource.response.CepResponse
import br.com.nicolas.brasilconsulta.data.repository.MainRepository
import com.google.gson.JsonElement
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class CepViewModel(
    private val repository: MainRepository
) : ViewModel() {

    private val _uiState = MutableLiveData<UiState<CepResponse>>()
    val uiState: LiveData<UiState<CepResponse>> get() = _uiState

    fun getCep(cepCode: String) = viewModelScope.launch {
        repository.fetchCep(cepCode).collect {
            when (it) {
                is Resource.Loading -> {
                    _uiState.value = UiState.Loading()
                }
                is Resource.Success -> {
                    _uiState.value = UiState.Success(it.item)
                    val a = it.item
                    a.cep
                    a.city
                }
                is Resource.Error -> {
                    _uiState.value = UiState.Error(it.throwable)
                }
            }
        }
    }
}