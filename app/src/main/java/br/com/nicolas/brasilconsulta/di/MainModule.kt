package br.com.nicolas.brasilconsulta.di

import br.com.nicolas.brasilconsulta.common.Constants
import br.com.nicolas.brasilconsulta.data.datasource.remote.ApiService
import br.com.nicolas.brasilconsulta.data.datasource.remote.RemoteDataSource
import br.com.nicolas.brasilconsulta.data.datasource.remote.RemoteDataSourceImpl
import br.com.nicolas.brasilconsulta.data.repository.MainRepository
import br.com.nicolas.brasilconsulta.data.repository.MainRepositoryImpl
import br.com.nicolas.brasilconsulta.ui.cep.CepViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val instance = module {

    single { provideRetrofit() }
    single { provideOkHttpClient() }
    single { provideRetrofitService(retrofit = get()) }

    factory<RemoteDataSource> {
        RemoteDataSourceImpl(
            apiService = get()
        )
    }

    factory<MainRepository> {
        MainRepositoryImpl(remote = get())
    }

    viewModel {
        CepViewModel(
            repository = get()
        )
    }
}

private fun provideRetrofit(): Retrofit {
    return Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(provideOkHttpClient())
        .build()
}

private fun provideOkHttpClient(): OkHttpClient {
    val httpLoggingInterceptor = HttpLoggingInterceptor()
    httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

    return OkHttpClient.Builder()
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .addInterceptor(httpLoggingInterceptor)
        .build()
}

private fun provideRetrofitService(retrofit: Retrofit): ApiService =
    retrofit.create(ApiService::class.java)
