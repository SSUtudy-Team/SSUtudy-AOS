package com.android.ssutudy.presentation.signup.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import com.android.ssutudy.R
import com.android.ssutudy.databinding.FragmentSignUpSecondBinding
import com.android.ssutudy.util.extensions.submitList

class SignUpSecondFragment : Fragment() {
    private var _binding: FragmentSignUpSecondBinding? = null
    private val binding: FragmentSignUpSecondBinding
        get() = requireNotNull(_binding)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentSignUpSecondBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.layoutSignUpSecondCategory.submitList(
            listOf("s123", "조차누", "김준서", "임화랑", "12312309213909")
        )

        binding.spinnerLoginSecondGrade.adapter = ArrayAdapter.createFromResource(
            requireContext(),
            R.array.spinner_grade, R.layout.item_sign_up_second_spinner
        )
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}