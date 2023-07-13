package com.example.suitmedia.presentation.screen2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.example.suitmedia.databinding.ActivityUserBinding
import com.example.suitmedia.presentation.screen3.ListActivity



class UserActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUserBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val toolbar = binding.toolbar
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Second Screen"
        val nameScreenOne = intent.getStringExtra(NAME_SCREEN_ONE)
        val nameScreenTwo = intent.getStringExtra(NAME_SCREEN_TWO)

        if (!nameScreenOne.isNullOrEmpty()) {
            binding.tvName1.text = nameScreenOne
        }
        if (!nameScreenTwo.isNullOrEmpty()) {
            binding.text2.text = nameScreenTwo
        }

        binding.btnChoose.setOnClickListener{
            val intent = Intent(this, ListActivity::class.java)
            intent.putExtra(ListActivity.NAME_SCREEN_ONE, nameScreenOne)
            startActivity(intent)
        }
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
    companion object {
        const val NAME_SCREEN_ONE = "name_screen_one"
        const val NAME_SCREEN_TWO = "name_screen_two"

    }
}