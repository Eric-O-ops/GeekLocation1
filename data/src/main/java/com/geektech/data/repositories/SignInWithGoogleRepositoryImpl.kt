package com.geektech.data.repositories

import com.geektech.domain.repositories.SignInWithGoogleRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import javax.inject.Inject

class SignInWithGoogleRepositoryImpl @Inject constructor(
    private val auth: FirebaseAuth
) : SignInWithGoogleRepository {

    override fun firebaseWithOneTap(idToken: String, onSuccess: () -> Unit, onError: () -> Unit) {
        val firebaseCredential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(firebaseCredential)
            .addOnCompleteListener { task ->

                if (task.isSuccessful) onSuccess() else onError()
            }
    }
}