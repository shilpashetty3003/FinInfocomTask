package com.example.fininfocomtask.common.utils

import android.app.Activity
import android.content.Context
import android.util.Log
import com.example.fininfocomtask.R

object Validations {


    fun isValidForLogin(username: String, password: String,context: Context): String {
        if(username.length ==0 || password.length ==0){
            return context.getString(R.string.username_null)
        }
        if (username.length != 10 || password.length < 7) {
            Log.d("TAG", "isValidForSignUp: ${username.length}  ${password.length}")
            return context.getString(R.string.username_password_length)
        }
        if (!password.any { it.isUpperCase() }) {
            return context.getString(R.string.password_validation)
        }
        val specialCharacters = "!@#$%^&*()_-+={}[]|:;<>,.?/~"
        if (!password.any { specialCharacters.contains(it) }) {
            return context.getString(R.string.password_validation)
        }
        if (!password.any { it.isDigit() }) {
            return context.getString(R.string.password_validation)
        }

        return ""
    }

    fun isValidForSignUp(
        username: String,
        password: String,
        confirmPassword: String,
        firstName: String,
        lastName: String,
        context: Context
    ): String {
        if (firstName.length == 0 || lastName.length == 0) {
            return context.getString(R.string.name_null)
        }
        if(username.length ==0 || password.length ==0){
            return context.getString(R.string.username_null)
        }
        if (username.length != 10 || password.length < 7) {
            Log.d("TAG", "isValidForSignUp: ${username.length}  ${password.length}")
            return context.getString(R.string.username_password_length)
        }
        if (!password.any { it.isUpperCase() }) {
            return context.getString(R.string.password_validation)
        }
        val specialCharacters = "!@#$%^&*()_-+={}[]|:;<>,.?/~"
        if (!password.any { specialCharacters.contains(it) }) {
            return context.getString(R.string.password_validation)
        }
        if (!password.any { it.isDigit() }) {
            return context.getString(R.string.password_validation)
        }
        if (!password.equals(confirmPassword)) {
            return context.getString(R.string.pwd_equals_confirm)
        }
        return ""
    }


    fun isUserValid(name:String,age:String,city:String,context: Context):String{
        if(name.isEmpty()) return context.getString(R.string.name_error)
        if(age.isEmpty()) return context.getString(R.string.age_error)
        if(city.isEmpty()) return context.getString(R.string.city_error)
        return ""
    }


}