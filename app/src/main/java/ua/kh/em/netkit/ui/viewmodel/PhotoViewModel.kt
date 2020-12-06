package ua.kh.em.netkit.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import ua.kh.em.netkit.data.model.Photo
import ua.kh.em.netkit.data.repository.AppRepository


class PhotoViewModel : ViewModel() {
    private val appRepository: AppRepository = AppRepository()

    fun loadPhotos(): LiveData<List<Photo>?> {
        return appRepository.loadPhotos()
    }
}