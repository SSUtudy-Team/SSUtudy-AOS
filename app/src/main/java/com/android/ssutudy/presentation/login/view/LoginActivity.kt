package com.android.ssutudy.presentation.login.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.android.ssutudy.databinding.ActivityLoginBinding
import com.android.ssutudy.presentation.main.view.MainActivity
import com.android.ssutudy.presentation.signup.view.SignUpActivity

class LoginActivity : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSignUpTextViewClickEvent()
        setLoginBtnClickEvent()
    }

    private fun setLoginBtnClickEvent() {
        binding.btnLogin.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            if (!isFinishing) finish()
        }
    }

    private fun setSignUpTextViewClickEvent() {
        binding.tvLoginSignUp.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
        }
    }
}