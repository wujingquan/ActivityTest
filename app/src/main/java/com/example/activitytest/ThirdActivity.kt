package com.example.activitytest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.activitytest.databinding.ActivityThirdBinding

class ThirdActivity : AppCompatActivity() {
    private lateinit var binding: ActivityThirdBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityThirdBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.button3.setOnClickListener { finish() }
    }
}