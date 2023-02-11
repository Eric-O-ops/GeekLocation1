package com.geektech.data.repositories

import com.geektech.domain.base.constansts.Constants
import com.geektech.domain.repositories.SaveUserDataRepository
import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject

class SafeUserDataRepositoryImpl @Inject constructor(
    private val firestore: FirebaseFirestore
): SaveUserDataRepository  {

    override fun saveUserData(name: String) {
        val user = hashMapOf(
            Constants.Firebase.nameOfUsernameField to name,
        )

        firestore.collection(Constants.Firebase.nameOfCollation).add(user)
    }
}