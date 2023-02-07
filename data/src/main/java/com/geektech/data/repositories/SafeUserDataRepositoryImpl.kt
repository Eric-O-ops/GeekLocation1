package com.geektech.data.repositories

import com.geektech.domain.repositories.SaveUserDataRepository
import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject

class SafeUserDataRepositoryImpl @Inject constructor(
    private val firestore: FirebaseFirestore
): SaveUserDataRepository  {

    override fun saveData(name: String) {
        val user = hashMapOf(
            "name" to name,
        )

        firestore.collection("Users").add(user)
    }
}