package com.example.fininfocomtask.common.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.fininfocomtask.R
import com.example.fininfocomtask.databinding.UserListLayoutBinding
import com.example.fininfocomtask.model.User
import javax.inject.Inject

class UserAdapter @Inject constructor() : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    private var userList = arrayListOf<User>()

    inner class UserViewHolder(val binding: UserListLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(user: User) {
            Log.d("TAG", "bind: $user")
            binding.user = user

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(
           DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.user_list_layout,parent,false)
        )
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(userList.get(position))
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    fun updateAdapter(userList: List<User>) {
        Log.d("TAG", "updateAdapter:  $userList")
        this.userList.clear()
        this.userList.addAll(userList)
        notifyDataSetChanged()

    }
}