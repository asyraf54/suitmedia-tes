package com.example.suitmedia.presentation.screen1

import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View

import android.view.WindowManager
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.suitmedia.data.response.User
import com.example.suitmedia.databinding.ActivityMainBinding
import com.example.suitmedia.presentation.screen2.UserActivity



class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = Color.TRANSPARENT
            window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)
        }

        binding.btnNext.setOnClickListener{
            val intent = Intent(this, UserActivity::class.java)
            startActivity(intent)
        }

        binding.btnCheck.setOnClickListener {
            val sentence = binding.etPalindrome.text.toString()
            val isPalindrome = isPalindrome(sentence)

            val message = if (isPalindrome) "Palindrome" else "Not Palindrome"
            showDialog(message)
        }

        binding.btnNext.setOnClickListener {
            val name = binding.etName.text.toString()
            val intent = Intent(this, UserActivity::class.java)
            intent.putExtra(UserActivity.NAME_SCREEN_ONE, name)
            startActivity(intent)
        }

    }
    fun isPalindrome(sentence: String): Boolean {
        val cleanSentence = sentence.replace("\\s+".toRegex(), "").toLowerCase()
        val reversedSentence = cleanSentence.reversed()

        return cleanSentence == reversedSentence
    }

    fun showDialog(message: String) {
        val alertDialog = AlertDialog.Builder(this)
            .setMessage(message)
            .setPositiveButton("OK", null)
            .create()

        alertDialog.show()
    }
}