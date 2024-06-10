package com.okumu.swiftdemo.ui.screens.auth

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.NavHostFragment
import com.okumu.swiftdemo.R
import com.okumu.swiftdemo.databinding.ActivityAuthBinding

class AuthActivity : AppCompatActivity(){
    private lateinit var binding: ActivityAuthBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_auth)
        
        val hostFragment = supportFragmentManager
            .findFragmentById(R.id.auth_host) as NavHostFragment 
    }
}