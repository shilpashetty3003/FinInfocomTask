package com.example.fininfocomtask.ui

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.fininfocomtask.R
import com.example.fininfocomtask.common.utils.Extensions.toast
import com.example.fininfocomtask.common.utils.FirebaseUtils
import com.example.fininfocomtask.common.utils.Validations
import com.example.fininfocomtask.databinding.ActivitySignUpScreenBinding

class SignUpScreen : BaseActivity<ActivitySignUpScreenBinding>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()

    }

    override fun getViewBinding() = ActivitySignUpScreenBinding.inflate(layoutInflater)
    fun onSignUpClick(view: View) {

        val userName = binding.edUsername.text.toString()
        val password = binding.edPassword.text.toString()
        val confirmPassword = binding.edConfirmPassword.text.toString()
        val firstName = binding.edFirstName.text.toString()
        val lastName = binding.edLastName.text.toString()
        val isValid = Validations.isValidForSignUp(
            userName, password, confirmPassword, firstName, lastName, this
        )

        if (isValid.equals("")) {
            showProgressDialog("Loading...")
            FirebaseUtils.firebaseAuth.createUserWithEmailAndPassword(
                "$userName@domain.com",
                password
            ).addOnCompleteListener {
                hideProgressDialog()
                if (it.isSuccessful) {
                    toast(getString(R.string.successfully_signIn))
                    val intent = Intent(this, MainActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                    startActivity(intent)
                    finish()

                }
            }.addOnFailureListener {
                hideProgressDialog()
                toast(it.localizedMessage)
            }
        } else {
            toast(isValid)
        }
    }
}