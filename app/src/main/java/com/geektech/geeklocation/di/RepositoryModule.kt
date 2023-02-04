package com.geektech.geeklocation.di

import com.geektech.data.repositories.SignInWithGoogleRepositoryImpl
import com.geektech.domain.repositories.SignInWithGoogleRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    fun bindPokemonRepository(repository: SignInWithGoogleRepositoryImpl): SignInWithGoogleRepository
}