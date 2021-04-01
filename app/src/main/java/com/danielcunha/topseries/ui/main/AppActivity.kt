package com.danielcunha.topseries.ui.main

import android.os.Bundle
import androidx.annotation.CallSuper
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.danielcunha.topseries.R
import com.danielcunha.topseries.databinding.ActivityAppBinding

class AppActivity : AppCompatActivity() {

    lateinit var binding: ActivityAppBinding

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_app)
    }
}