package ua.kh.em.netsimple.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import ua.kh.em.netsimple.data.model.Photo
import ua.kh.em.netsimple.data.repository.AppRepository


class PhotoViewModel : ViewModel() {
    private val appRepository: AppRepository = AppRepository()

    fun loadPhotos(): LiveData<List<Photo>?> {
        return appRepository.loadPhotos()
    }
}