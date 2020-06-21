package ua.kh.em.netsimple.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import ua.kh.em.netsimple.model.Photo
import ua.kh.em.netsimple.repository.AppRepository


class PhotoViewModel : ViewModel() {
    private val appRepository: AppRepository = AppRepository()

    fun loadPhotos(): LiveData<List<Photo>?> {
        return appRepository.loadPhotos()
    }
}