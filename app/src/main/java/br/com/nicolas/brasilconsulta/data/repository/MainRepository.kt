package br.com.nicolas.brasilconsulta.data.repository

import br.com.nicolas.brasilconsulta.common.Resource
import br.com.nicolas.brasilconsulta.data.datasource.response.CepResponse
import br.com.nicolas.brasilconsulta.data.datasource.response.FeriadosResponse
import br.com.nicolas.brasilconsulta.data.datasource.response.FeriadosResponseItem
import kotlinx.coroutines.flow.Flow

interface MainRepository {

    suspend fun fetchCep(cep: String): Flow<Resource<CepResponse>>

    suspend fun fetchDirect(direct: String): Flow<Resource<List<String>>>

    suspend fun fetchHolidays(year: String): Flow<Resource<FeriadosResponse>>

}