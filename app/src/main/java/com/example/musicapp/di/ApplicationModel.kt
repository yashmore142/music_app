package com.example.musicapp.di

import android.content.Context
import com.example.dagger_hilt_demo.api_call_setup.AuthIntersepter
import com.example.musicapp.apisetup.ApiClient
import com.example.musicapp.apisetup.ApiInterface
import com.example.musicapp.apisetup.Repository
import com.example.musicapp.apisetup.RepositoryImpl
import com.example.musicapp.utils.SessionManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class ApplicationModel {

    @Provides
    @Singleton
    fun provideAPIInterface(authIntersepter: AuthIntersepter): ApiInterface {
        return ApiClient.getClient(authIntersepter)!!.create(ApiInterface::class.java)
    }

    @Provides
    @Singleton
    fun provideRepository(apiInterface: ApiInterface): Repository {
        return RepositoryImpl(apiInterface)
    }

    @Provides
    @Singleton
    fun provideAuthIntersepter(sessionManager: SessionManager): AuthIntersepter {
        return AuthIntersepter(sessionManager)
    }
    @Provides
    @Singleton
    fun providesSessionManager(@ApplicationContext context: Context): SessionManager {
        return SessionManager(context)
    }
}