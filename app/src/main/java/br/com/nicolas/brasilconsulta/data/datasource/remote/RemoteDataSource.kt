package br.com.nicolas.brasilconsulta.data.datasource.remote

import br.com.nicolas.brasilconsulta.data.datasource.response.CepResponse
import br.com.nicolas.brasilconsulta.data.datasource.response.DirectResponse

interface RemoteDataSource {

    suspend fun getCep(cep: String): CepResponse

    suspend fun getDirect(directCode: String): DirectResponse

}