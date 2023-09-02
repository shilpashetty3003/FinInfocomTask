package com.example.fininfocomtask.ui

import android.app.ProgressDialog
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<VB : ViewBinding> : AppCompatActivity() {

    lateinit var binding: VB
    private var progressDialog: ProgressDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = getViewBinding()
        setContentView(binding.root)
    }

    fun showProgressDialog(message: String) {
        progressDialog?.let {
            if (!it.isShowing) {
                it.setMessage(message)
                it.setCancelable(false)
                it.show()
            }
        } ?: run {
            progressDialog = ProgressDialog(this).apply {
                setMessage(message)
                setCancelable(false)
                show()
            }
        }
    }

    fun hideProgressDialog() {
        progressDialog?.let {
            if (it.isShowing) {
                it.dismiss()
            }
        }
    }
    abstract fun getViewBinding(): VB
}