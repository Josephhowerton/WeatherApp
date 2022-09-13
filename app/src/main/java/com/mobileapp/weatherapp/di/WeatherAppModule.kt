package com.mobileapp.weatherapp.di

import com.mobileapp.weatherapp.network.RepositoryImpl
import com.mobileapp.weatherapp.network.interfaces.Repository
import com.mobileapp.weatherapp.network.interfaces.WeatherService
import com.mobileapp.weatherapp.util.Constants
import com.mobileapp.weatherapp.view.navigation.Navigator
import dagger.Module
import dagger.Provides
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class WeatherAppModule {

    @Provides
    @Singleton
    fun provideNavigator() : Navigator = Navigator()

    @Provides
    @Reusable
    fun provideOkHttp(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()
    }

    @Provides
    @Reusable
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(Constants.BASE_URL)
            .build()

    @Provides
    @Reusable
    fun provideMovieService(retrofit: Retrofit): WeatherService = retrofit.create(WeatherService::class.java)


    @Provides
    @Reusable
    fun provideRepository(repositoryImpl: RepositoryImpl): Repository = repositoryImpl
}