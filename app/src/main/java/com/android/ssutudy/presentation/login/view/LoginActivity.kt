package com.android.ssutudy.presentation.login.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.android.ssutudy.R
import com.android.ssutudy.data.local.SharedPreferences
import com.android.ssutudy.databinding.ActivityLoginBinding
import com.android.ssutudy.presentation.base.BaseDataBindingActivity
import com.android.ssutudy.presentation.login.viewmodel.LoginViewModel
import com.android.ssutudy.presentation.main.view.MainActivity
import com.android.ssutudy.presentation.signup.view.SignUpActivity
import com.android.ssutudy.util.extensions.makeToastMessage
import com.android.ssutudy.util.publics.PublicString.TOKEN
import com.android.ssutudy.util.publics.PublicString.USER_ID

class LoginActivity : BaseDataBindingActivity<ActivityLoginBinding>(R.layout.activity_login) {
    private val viewModel by viewModels<LoginViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setClickEvents()
        initObservers()
        setAutoLogin()
    }

    private fun setAutoLogin() {
        if (SharedPreferences.getString(TOKEN) != null) {
            startActivity(
                Intent(
                    this, MainActivity::class.java
                )
            )
            if (!isFinishing) finish()
        }
    }

    private fun initObservers() {
        initSuccessResponseObserver()
        initErrorResponseObserver()
    }

    private fun initErrorResponseObserver() {
        viewModel.loginErrorResponse.observe(this) {
            makeToastMessage(it)
        }
    }

    private fun initSuccessResponseObserver() {
        viewModel.loginSuccessResponse.observe(this) {
            SharedPreferences.setString(TOKEN, it.data.token)
            SharedPreferences.setString(USER_ID, it.data.userId.toString())
            startActivity(Intent(this, MainActivity::class.java))
            if (!isFinishing) finish()
        }
    }

    private fun setClickEvents() {
        setSignUpTextViewClickEvent()
        setLoginBtnClickEvent()
    }

    override fun bindViewModelWithBinding() {
        binding.vm = viewModel
    }

    private fun setLoginBtnClickEvent() {
        binding.btnLogin.setOnClickListener {
            viewModel.login()
        }
    }

    private fun setSignUpTextViewClickEvent() {
        binding.tvLoginSignUp.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
        }
    }
}