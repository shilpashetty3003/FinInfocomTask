package com.example.fininfocomtask.ui

import android.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import com.example.fininfocomtask.common.viewmodel.UserViewModel
import com.example.fininfocomtask.databinding.BottomSheetLayoutBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint


class BottomSheetFragment : BottomSheetDialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Customize your bottom sheet content here
        // Customize your bottom sheet content here
        val binding = BottomSheetLayoutBinding.inflate(layoutInflater)
        return binding.root
    }

}