package com.android.ssutudy.presentation.signup.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import com.android.ssutudy.R
import com.android.ssutudy.databinding.ActivitySignUpBinding
import com.android.ssutudy.listeners.signup.OnSignUpNextButtonClickListener

class SignUpActivity : AppCompatActivity(), OnSignUpNextButtonClickListener {
    private lateinit var binding: ActivitySignUpBinding
    private val signUpFirstFragment by lazy { SignUpFirstFragment() }
    private val signUpSecondFragment by lazy { SignUpSecondFragment() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewBinding()

        setFirstFragment(savedInstanceState)
    }

    private fun initViewBinding() {
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }


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