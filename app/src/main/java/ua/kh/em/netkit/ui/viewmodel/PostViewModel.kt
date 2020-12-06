package ua.kh.em.netkit.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import ua.kh.em.netkit.data.model.Post
import ua.kh.em.netkit.data.repository.AppRepository


class PostViewModel(application: Application) : AndroidViewModel(application) {

    private val appRepository: AppRepository = AppRepository()

    fun loadPosts(): LiveData<List<Post>?> {
        return appRepository.loadPosts()
    }
}