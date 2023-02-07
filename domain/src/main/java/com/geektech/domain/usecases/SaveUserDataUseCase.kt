package com.geektech.domain.usecases

import com.geektech.domain.repositories.SaveUserDataRepository
import javax.inject.Inject

class SaveUserDataUseCase @Inject constructor(
    private val repository: SaveUserDataRepository
) {
    operator fun invoke(name: String) = repository.saveData(name)
}