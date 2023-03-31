package com.lanazirot.pokedex

import com.lanazirot.pokedex.domain.implementations.PokemonLocalRepository
import com.lanazirot.pokedex.domain.implementations.PokemonRepository
import com.lanazirot.pokedex.domain.implementations.UserManager
import com.lanazirot.pokedex.domain.interfaces.*
import com.lanazirot.pokedex.domain.services.PokemonService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providePokemonRepository(): IPokemonRepository = PokemonRepository()


    @Provides
    @Singleton
    fun providePokemonService(): IPokemonService =  PokemonService(pokemonRepository = providePokemonRepository())

    @Provides
    @Singleton
    fun providePokemonLocalRepository(): IPokemonLocalRepository = PokemonLocalRepository(pokemonRepository = providePokemonRepository())

    @Provides
    @Singleton
    fun provideUserManager(): IUserManager = UserManager(pokemonLocalRepository = providePokemonLocalRepository())

}