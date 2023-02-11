package com.geektech.domain.repositories

interface SignInWithGoogleRepository {

    fun firebaseWithOneTap(idToken: String, onSuccess: () -> Unit, onError: () -> Unit)
}