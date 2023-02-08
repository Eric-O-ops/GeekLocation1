package com.geektech.presentation.ui.fragments.sign.`in`

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.geektech.domain.base.constansts.Constants
import com.geektech.presentation.R
import com.geektech.presentation.base.BaseFragment
import com.geektech.presentation.databinding.FragmentSignInBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SignInFragment :
    BaseFragment<FragmentSignInBinding, SignInViewModel>(R.layout.fragment_sign_in) {

    @Inject
    lateinit var gsc: GoogleSignInClient
    override val binding by viewBinding(FragmentSignInBinding::bind)
    override val viewModel: SignInViewModel by viewModels()

    override fun setupListener() {
        with(binding) {
            btnGoogle.setOnClickListener {
                if (etInputName.text.isNotEmpty()) {
                    etInputName.apply {
                        setBackgroundResource(R.drawable.normal_edit_text)
                        setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0)
                    }
                    resultLauncher.launch(gsc.signInIntent)
                } else {
                    etInputName.apply {
                        setBackgroundResource(R.drawable.error_edit_text)
                        setCompoundDrawablesWithIntrinsicBounds(
                            0,
                            0,
                            R.drawable.ic_priority_high,
                            0
                        )
                    }
                }
            }
        }
    }

    private val resultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
                val token = task.result.idToken
                if (token != null) {
                    viewModel.apply {
                        signInWithGoogle(token,
                            onSuccess = {
                                findNavController().navigate(R.id.action_signInFragment_to_homeFragment)
                            },
                            onError = {
                                Toast.makeText(
                                    requireContext(),
                                    Constants.textError, Toast.LENGTH_SHORT
                                ).show()
                            })

                        saveUserData(binding.etInputName.text.toString())
                    }
                } else {
                    Log.e("TokenException", "No Token!")
                }
            }
        }
}
