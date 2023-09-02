package com.example.fininfocomtask.repository

import com.example.fininfocomtask.realm.UserDatabaseOperations
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class UserRepositoryImp @Inject constructor(private val databaseOperations: UserDatabaseOperations) :
    UserRepository {
    override fun addUser(name: String, age: Int, city: String): Flow<UserStatus> = flow {
        emit(UserStatus.Loading)
        databaseOperations.insertUser(name, age, city)
        emit(UserStatus.Added)
    }.flowOn(Dispatchers.IO)

    override fun getUsers(): Flow<UserStatus> = flow<UserStatus> {
        emit(UserStatus.Loading)
        val userResult = databaseOperations.retrieveUser()
        emit(UserStatus.Result(userResult))
    }.flowOn(Dispatchers.IO)


}