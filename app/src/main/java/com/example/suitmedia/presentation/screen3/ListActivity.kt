package com.example.suitmedia.presentation.screen3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.suitmedia.databinding.ActivityListBinding
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.PagingData
import com.example.suitmedia.data.response.User
import com.example.suitmedia.presentation.screen2.UserActivity
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class ListActivity : AppCompatActivity() {
    private lateinit var adapter: ListAdapter
    private lateinit var binding: ActivityListBinding
    var nameScreenOne: String? = ""
    private val listViewModel: ListViewModel by viewModels(){
        ViewModelFactory()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val toolbar = binding.toolbar
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Third Screen"
        nameScreenOne = intent.getStringExtra(UserActivity.NAME_SCREEN_ONE)

        val layoutManager = LinearLayoutManager(this)
        binding.rvUser.layoutManager = layoutManager


        setupRecylerView()
        loadData()

    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
    private fun setupRecylerView() {
        adapter = ListAdapter(nameScreenOne)
        binding.rvUser.adapter = adapter


    }
    private fun loadData() {
        lifecycleScope.launch {
            listViewModel.users.collectLatest { pagingData ->
                adapter.submitData(pagingData)
            }
        }
    }

    companion object {
        const val NAME_SCREEN_ONE = "name_screen_one"
    }

}