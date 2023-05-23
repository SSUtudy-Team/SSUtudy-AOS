package com.android.ssutudy.presentation.signup.view

import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.commit
import com.android.ssutudy.R
import com.android.ssutudy.databinding.ActivitySignUpBinding
import com.android.ssutudy.listeners.signup.OnSignUpNextButtonClickListener
import com.android.ssutudy.presentation.base.BaseViewBindingActivity

class SignUpActivity : BaseViewBindingActivity<ActivitySignUpBinding>(),
    OnSignUpNextButtonClickListener {
    private val signUpFirstFragment by lazy { SignUpFirstFragment() }
    private val signUpSecondFragment by lazy { SignUpSecondFragment() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setFirstFragment(savedInstanceState)
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