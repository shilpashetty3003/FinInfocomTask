package com.example.fininfocomtask.di

import com.example.fininfocomtask.common.adapter.UserAdapter
import com.example.fininfocomtask.realm.UserDatabaseOperations
import com.example.fininfocomtask.repository.UserRepository
import com.example.fininfocomtask.repository.UserRepositoryImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.realm.RealmConfiguration
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
class UserModule {


    private val realmVersion = 1L


    @Singleton
    @Provides
    fun providesRealmConfig(): RealmConfiguration =
        RealmConfiguration.Builder()
            .schemaVersion(realmVersion)
            .build()

    @Singleton
    @Provides
    fun provideUserRepository(databaseOperations: UserDatabaseOperations): UserRepository {
        return UserRepositoryImp(databaseOperations)
    }
    @Singleton
    @Provides
    fun providesUserAdapter():UserAdapter{
        return UserAdapter()
    }
}