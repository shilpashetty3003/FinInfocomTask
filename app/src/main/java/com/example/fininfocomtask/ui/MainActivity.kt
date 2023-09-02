package com.example.fininfocomtask.ui

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.annotation.StringRes
import com.example.fininfocomtask.R
import com.example.fininfocomtask.common.adapter.UserAdapter
import com.example.fininfocomtask.common.utils.Extensions.toast
import com.example.fininfocomtask.common.utils.Validations
import com.example.fininfocomtask.common.viewmodel.UserViewModel
import com.example.fininfocomtask.databinding.ActivityMainBinding
import com.example.fininfocomtask.databinding.BottomSheetLayoutBinding
import com.example.fininfocomtask.model.User
import com.example.fininfocomtask.repository.UserStatus
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {


    private val userViewModel: UserViewModel by viewModels()
    private var fullUserArrayList: MutableList<User> = mutableListOf()
    var filterUserArrayList: MutableList<User> = mutableListOf()
    private var isCheckedByDesc: Boolean = false
    private var sortBy: String = "Name"


    @Inject
    lateinit var userAdapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setUpListeners()
        setUpObserver()


    }

    private fun setUpListeners() {

        binding.rvUsers.adapter = userAdapter
        binding.tvSortType.text =
            "Sort By ${sortBy} in ${if (isCheckedByDesc) "Descending Order" else "Ascending Order"}"
        binding.fab.setOnClickListener {
            openBottomSheet()
        }
    }

    private fun openBottomSheet() {
        val binding = BottomSheetLayoutBinding.inflate(layoutInflater)
        val dialog = BottomSheetDialog(this)
        dialog.setContentView(binding.root)
        val button = binding.btnAdd
        button.setOnClickListener {

            val isValid = Validations.isUserValid(
                binding.edName.text.toString(),
                binding.edAge.text.toString(),
                binding.edCity.text.toString(), this
            )
            if (isValid.equals("")) {
                userViewModel.addData(
                    binding.edName.text.toString(),
                    binding.edAge.text.toString().toInt(),
                    binding.edCity.text.toString()
                )
                userViewModel.getUserData()
                dialog.dismiss()
            } else {
                toast(isValid)
            }


        }

        dialog.show()
    }

    private fun setUpObserver() {

        userViewModel.userDataStatus.observe(this) { status ->
            when (status) {
                UserStatus.Loading -> {

                    showProgressDialog("Loading")
                }
                is UserStatus.Added -> {
                    hideProgressDialog()
                    createSnackbar(R.string.user_add)
                }
                is UserStatus.Result -> {
                    hideProgressDialog()
                    Log.d("TAG", "setUpObserver: ${status.userList} ")
                    fullUserArrayList = status.userList as ArrayList<User>
                    sortData(sortBy, isCheckedByDesc)
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_sort_name -> {
                sortBy = "Name"
                sortData(sortBy, isCheckedByDesc)
                return true
            }
            R.id.action_sort_age -> {
                sortBy = "Age"
                sortData(sortBy, isCheckedByDesc)
                return true
            }
            R.id.action_sort_city -> {
                sortBy = "City"
                sortData(sortBy, isCheckedByDesc)
                return true
            }
            R.id.action_descending -> {

                item.setChecked(!item.isChecked)
                isCheckedByDesc = item.isChecked
                sortData(sortBy, isCheckedByDesc)
                return true
            }

            else -> return super.onOptionsItemSelected(item)
        }
    }


    fun sortData(sortName: String, isCheckedByDesc: Boolean) {

        Log.d("TAG", "sortData: $sortName  $isCheckedByDesc")
        when (sortName) {
            "Name" -> {
                if (isCheckedByDesc) {
                    filterUserArrayList = fullUserArrayList.sortedByDescending {
                        it.name.toUpperCase()
                    } as MutableList<User>
                    userAdapter.updateAdapter(filterUserArrayList)
                } else {
                    filterUserArrayList = ArrayList(fullUserArrayList)
                    filterUserArrayList.sortBy { it.name }
                    userAdapter.updateAdapter(filterUserArrayList)
                }
            }

            "Age" -> {
                if (isCheckedByDesc) {
                    filterUserArrayList = fullUserArrayList.sortedByDescending {
                        it.age
                    } as MutableList<User>
                    userAdapter.updateAdapter(filterUserArrayList)
                } else {
                    filterUserArrayList = ArrayList(fullUserArrayList)
                    filterUserArrayList.sortBy { it.age }
                    userAdapter.updateAdapter(filterUserArrayList)
                }
            }


            "City" -> {
                if (isCheckedByDesc) {
                    filterUserArrayList = fullUserArrayList.sortedByDescending {
                        it.city.toUpperCase()
                    } as MutableList<User>
                    userAdapter.updateAdapter(filterUserArrayList)
                } else {
                    filterUserArrayList = ArrayList(fullUserArrayList)
                    filterUserArrayList.sortBy { it.city }
                    userAdapter.updateAdapter(filterUserArrayList)
                }
            }

        }
        binding.tvSortType.text =
            "Sort By ${sortBy} in ${if (isCheckedByDesc) "Descending Order" else "Ascending Order"}"
    }

    private fun createSnackbar(@StringRes message: Int) {
        binding?.apply {
            Snackbar.make(root, message, Snackbar.LENGTH_SHORT).show()
        }
    }

    override fun getViewBinding() = ActivityMainBinding.inflate(layoutInflater)
}