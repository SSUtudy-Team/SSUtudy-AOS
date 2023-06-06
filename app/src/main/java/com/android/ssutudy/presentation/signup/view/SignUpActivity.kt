package com.android.ssutudy.presentation.signup.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.activity.viewModels
import androidx.fragment.app.commit
import com.android.ssutudy.R
import com.android.ssutudy.databinding.ActivitySignUpBinding
import com.android.ssutudy.listeners.signup.OnSignUpNextButtonClickListener
import com.android.ssutudy.presentation.base.BaseViewBindingActivity
import com.android.ssutudy.presentation.signup.viewmodel.SignUpViewModel
import com.android.ssutudy.util.extensions.makeToastMessage

class SignUpActivity : BaseViewBindingActivity<ActivitySignUpBinding>(),
    OnSignUpNextButtonClickListener {
    private val viewModel by viewModels<SignUpViewModel>()
    private val signUpFirstFragment by lazy { SignUpFirstFragment() }
    private val signUpSecondFragment by lazy { SignUpSecondFragment() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setFirstFragment(savedInstanceState)
        initObservers()
    }

    private fun initObservers() {
        initSuccessResponseObserver()
        initErrorResponseObserver()
    }

    private fun initErrorResponseObserver() {
        viewModel.signUpErrorResponse.observe(this) {
            makeToastMessage(it)
            Log.e("gio", it)
        }
    }

    private fun initSuccessResponseObserver() {
        viewModel.signUpSuccessResponse.observe(this) {
            makeToastMessage("회원 가입 성공")
            Log.e("gio", it.toString())
            if (!isFinishing) finish()
        }
    }

    override fun setBinding(layoutInflater: LayoutInflater): ActivitySignUpBinding =
        ActivitySignUpBinding.inflate(layoutInflater)

    private fun setFirstFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                add(R.id.fcv_sign_up, signUpFirstFragment)
            }
        }
    }

    override fun setNextFragment() {
        supportFragmentManager.commit {
            replace(R.id.fcv_sign_up, signUpSecondFragment)
        }
    }
}