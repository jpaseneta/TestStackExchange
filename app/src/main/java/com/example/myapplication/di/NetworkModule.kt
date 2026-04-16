package com.example.myapplication.di

import com.example.myapplication.data.StackExchangeService
import com.example.myapplication.data.UserRepositoryImpl
import com.example.myapplication.domain.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.stackexchange.com/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideStackExchangeService(retrofit: Retrofit): StackExchangeService {
        return retrofit.create(StackExchangeService::class.java)
    }

    @Provides
    @Singleton
    fun provideUserRepository(service: StackExchangeService): UserRepository {
        return UserRepositoryImpl(service)
    }
}
