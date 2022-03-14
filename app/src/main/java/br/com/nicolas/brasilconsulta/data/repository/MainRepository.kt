package br.com.nicolas.brasilconsulta.data.repository

import br.com.nicolas.brasilconsulta.common.Resource
import br.com.nicolas.brasilconsulta.data.datasource.response.CepResponse
import kotlinx.coroutines.flow.Flow

interface MainRepository {

    suspend fun fetchCep(cep: String): Flow<Resource<CepResponse>>

}