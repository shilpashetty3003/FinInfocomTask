package com.example.fininfocomtask.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.fininfocomtask.R
import com.example.fininfocomtask.common.utils.Extensions.toast
import com.example.fininfocomtask.common.utils.FirebaseUtils.firebaseAuth
import com.example.fininfocomtask.common.utils.Validations
import com.example.fininfocomtask.common.utils.Validations.isValidForLogin
import com.example.fininfocomtask.databinding.ActivityLoginScreenBinding

class LoginScreen : BaseActivity<ActivityLoginScreenBinding>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
    }

    override fun getViewBinding(): ActivityLoginScreenBinding {
        return ActivityLoginScreenBinding.inflate(layoutInflater)
    }

    fun onLoginClick(view: View) {
        val userName = binding.edUsername.text.toString()
        val password = binding.edPassword.text.toString()
        val isValid = isValidForLogin(userName, password, this)
        if (isValid.equals("")) {
            showProgressDialog("Loading")
            firebaseAuth.signInWithEmailAndPassword("$userName@domain.com", password)
                .addOnCompleteListener {
                    hideProgressDialog()
                    if (it.isSuccessful) {
                        toast(getString(R.string.successfully_login))
                        startActivity(Intent(this, MainActivity::class.java))
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

    fun goToSignUp(view: View) {
        startActivity(Intent(this, SignUpScreen::class.java))
    }
}