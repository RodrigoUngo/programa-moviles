package com.rungo.dummydictionary.ui.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.rungo.dummydictionary.DummyDictionaryApplication
import com.rungo.dummydictionary.R
import com.rungo.dummydictionary.ui.ViewModelFactory

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    val app by lazy {
        application as DummyDictionaryApplication
    }
    private val viewModelFactory by lazy {
        ViewModelFactory(app.getLoginRepository())
    }
    private val viewModel: LoginViewModel by viewModels {
        viewModelFactory
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if(app.isUserLogin()){
            return startMainActivity()
        }
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        binding.viewModel = viewModel
    }
}