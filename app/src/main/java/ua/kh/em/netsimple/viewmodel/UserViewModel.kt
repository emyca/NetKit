package ua.kh.em.netsimple.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import ua.kh.em.netsimple.model.User
import ua.kh.em.netsimple.repository.AppRepository


class UserViewModel : ViewModel() {

    private val appRepository: AppRepository = AppRepository()

    fun loadUsers(): LiveData<List<User>?> {
        return appRepository.loadUsers()
    }
}