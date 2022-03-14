package br.com.nicolas.brasilconsulta.data.datasource.remote

import br.com.nicolas.brasilconsulta.data.datasource.response.CepResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("cep/v1/{cep}")
    suspend fun getCep(
        @Path("cep") cepCode: String
    ): CepResponse

}