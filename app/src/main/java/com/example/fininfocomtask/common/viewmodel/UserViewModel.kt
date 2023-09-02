package com.example.fininfocomtask.common.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fininfocomtask.repository.UserRepository
import com.example.fininfocomtask.repository.UserStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(private val userRepository: UserRepository) : ViewModel() {

    private val _userDataStatus = MutableLiveData<UserStatus>()
    val userDataStatus: LiveData<UserStatus>
        get() = _userDataStatus

    init {
        getUserData()
    }

    fun addData(name: String, age: Int, city: String) {
        viewModelScope.launch {
            userRepository.addUser(name, age, city).collect {
                Log.d("TAG", "addData: ${it} ")
                _userDataStatus.value = it
            }
        }
    }

    fun getUserData() {
        viewModelScope.launch {
            userRepository.getUsers().collect {
                Log.d("TAG", "getUserData:  $it")
                _userDataStatus.value = it
            }
        }
    }
}