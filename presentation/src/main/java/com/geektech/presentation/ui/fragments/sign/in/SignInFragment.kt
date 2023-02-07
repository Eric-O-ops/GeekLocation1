package com.geektech.presentation.ui.fragments.sign.`in`

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.geektech.presentation.R
import com.geektech.presentation.databinding.FragmentSignInBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SignInFragment : Fragment(R.layout.fragment_sign_in) {

    @Inject
    lateinit var gsc: GoogleSignInClient
    private val binding by viewBinding(FragmentSignInBinding::bind)
    private val viewModel: SignInViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val resultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == Activity.RESULT_OK) {
                    val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
                    viewModel.apply {
                        signInWithGoogle(task.result.idToken!!,
                            onSuccess = {
                                findNavController().navigate(R.id.action_signInFragment_to_homeFragment)

                            },
                            onError = {
                                Toast.makeText(
                                    requireContext(),
                                    "something was wrong", Toast.LENGTH_SHORT
                                ).show()
                            })

                        saveUserData(binding.etInputName.text.toString())
                    }
                }
            }

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
}
