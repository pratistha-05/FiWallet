package com.app.finance.presentation

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity

@SuppressLint("CustomSplashScreen") class SplashActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    startActivity(Intent(this, MainActivity::class.java))
    finish()
  }
}
