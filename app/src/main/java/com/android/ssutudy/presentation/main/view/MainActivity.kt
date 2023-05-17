package com.android.ssutudy.presentation.main.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.android.ssutudy.R
import com.android.ssutudy.databinding.ActivityMainBinding
import com.android.ssutudy.presentation.create.view.CreateActivity
import com.android.ssutudy.presentation.home.view.HomeFragment
import com.android.ssutudy.presentation.mypage.view.MyPageFragment
import com.android.ssutudy.util.publics.PublicFunction.makeLog
import com.android.ssutudy.util.publics.PublicString.INVALID_ITEM_ACCESSED

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val homeFragment by lazy { HomeFragment() }
    private val myPageFragment by lazy { MyPageFragment() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        addFirstFragment(savedInstanceState)
        initBnvItemSelectEvent()
    }

    private fun initBnvItemSelectEvent() {
        with(binding.bnvMain) {
            setOnItemSelectedListener {
                when (it.itemId) {
                    R.id.menu_home -> changeFragment(homeFragment)
                    R.id.menu_my_page -> changeFragment(myPageFragment)
                    R.id.menu_create -> {
                        startActivity(
                            Intent(this@MainActivity, CreateActivity::class.java)
                        )
                        return@setOnItemSelectedListener false
                    }

                    else -> changeFragment(null)
                }
            }

            setOnItemReselectedListener {

            }
        }
    }

    private fun addFirstFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState != null)
            supportFragmentManager.commit {
                replace(R.id.fcv_main, homeFragment)
            }
    }

    private fun changeFragment(fragment: Fragment?): Boolean {
        if (fragment != null) {
            supportFragmentManager.commit {
                replace(R.id.fcv_main, fragment)
            }
            return true
        }
        makeLog(INVALID_ITEM_ACCESSED)
        return false
    }
}