package br.com.nicolas.brasilconsulta.ui.feriados

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.nicolas.brasilconsulta.common.Resource
import br.com.nicolas.brasilconsulta.common.UiState
import br.com.nicolas.brasilconsulta.data.datasource.response.FeriadosResponse
import br.com.nicolas.brasilconsulta.data.repository.MainRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class FeriadosViewModel(
    private val repository: MainRepository
) : ViewModel() {

    private val _uiState = MutableLiveData<UiState<FeriadosResponse>>()
    val uiState: LiveData<UiState<FeriadosResponse>> get() = _uiState

    fun getHolidays(year: String) = viewModelScope.launch {
        repository.fetchHolidays(year).collect { result ->
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