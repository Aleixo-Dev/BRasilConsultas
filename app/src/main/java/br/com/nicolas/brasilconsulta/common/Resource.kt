package br.com.nicolas.brasilconsulta.common

sealed class Resource<out T> {
    object Loading : Resource<Nothing>()
    data class Success<T>(val item: T) : Resource<T>()
    data class Error(val throwable: String) : Resource<Nothing>()
}