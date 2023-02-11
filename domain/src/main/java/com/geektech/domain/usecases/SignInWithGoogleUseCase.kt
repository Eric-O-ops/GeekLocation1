package com.geektech.domain.usecases

import com.geektech.domain.repositories.SignInWithGoogleRepository
import javax.inject.Inject

class SignInWithGoogleUseCase @Inject constructor(
    private val repository: SignInWithGoogleRepository
) {

    operator fun invoke(idToken: String, onSuccess: () -> Unit, onError: () -> Unit) =
        repository.firebaseWithOneTap(idToken, onSuccess, onError)
}