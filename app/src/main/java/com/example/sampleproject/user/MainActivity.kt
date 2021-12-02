package com.example.sampleproject.user

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.example.domain.model.Users
import com.example.sampleproject.R
import com.example.sampleproject.databinding.ActivityMainBinding
import com.example.sampleproject.user.adapter.UserAdapter
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val userViewModel: UserViewModel by viewModels()
    private lateinit var userAdapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()

        userViewModel.getData()

        initSubscriber()
    }

    private fun init(){
        userAdapter = UserAdapter()
        binding.recyclerview.apply {
            adapter = userAdapter
        }
    }

    private fun initSubscriber(){

        userViewModel.users
            .flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
            .onEach { users ->
                userAdapter.setData(users)
            }
            .launchIn(lifecycleScope)

        userViewModel.error
            .flowWithLifecycle(lifecycle,Lifecycle.State.STARTED)
            .onEach {
                Snackbar.make(binding.root,"$it",Snackbar.LENGTH_LONG).show()
            }
            .launchIn(lifecycleScope)

    }

}