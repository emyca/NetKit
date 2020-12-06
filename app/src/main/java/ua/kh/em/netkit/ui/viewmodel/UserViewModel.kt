package ua.kh.em.netkit.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import ua.kh.em.netkit.data.model.User
import ua.kh.em.netkit.data.repository.AppRepository


class UserViewModel : ViewModel() {

    private val appRepository: AppRepository = AppRepository()

    fun loadUsers(): LiveData<List<User>?> {
        return appRepository.loadUsers()
    }
}