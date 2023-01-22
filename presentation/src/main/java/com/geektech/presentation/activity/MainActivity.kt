package com.geektech.presentation.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.geektech.presentation.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        this.setTheme(R.style.Theme_GeekLocation)
    }
}