package br.com.nicolas.brasilconsulta.data.datasource.remote

import br.com.nicolas.brasilconsulta.data.datasource.response.CepResponse
import br.com.nicolas.brasilconsulta.data.datasource.response.DirectResponse
import br.com.nicolas.brasilconsulta.data.datasource.response.FeriadosResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("cep/v1/{cep}")
    suspend fun getCep(
        @Path("cep") cepCode: String
    ): CepResponse

    @GET("ddd/v1/{ddd}")
    suspend fun getDirect(
        @Path("ddd") directCode: String
    ): DirectResponse


    @GET("feriados/v1/{ano}")
    suspend fun getHolidays(
        @Path("ano") year : String
    ) : FeriadosResponse
}