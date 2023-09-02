package com.example.fininfocomtask.repository

import android.service.autofill.UserData
import com.example.fininfocomtask.model.User

sealed class UserStatus{
    object Loading:UserStatus()
    object Added:UserStatus()
    data class Result(val userList:List<User>):UserStatus()
}
