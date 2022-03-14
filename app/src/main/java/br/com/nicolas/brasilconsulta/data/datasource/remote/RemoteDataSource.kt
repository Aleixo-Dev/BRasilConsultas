package br.com.nicolas.brasilconsulta.data.datasource.remote

import br.com.nicolas.brasilconsulta.data.datasource.response.CepResponse

interface RemoteDataSource {

    suspend fun getCep(cep: String): CepResponse

}