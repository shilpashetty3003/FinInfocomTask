package com.example.fininfocomtask.repository

import kotlinx.coroutines.flow.Flow

interface UserRepository {

    fun addUser(name:String,age:Int,city:String):Flow<UserStatus>
    fun getUsers():Flow<UserStatus>


}