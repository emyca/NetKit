package ua.kh.em.netkit.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ua.kh.em.netkit.data.model.Photo
import ua.kh.em.netkit.data.model.Post
import ua.kh.em.netkit.data.model.User
import ua.kh.em.netkit.data.network.ApiClient
import java.util.ArrayList

class AppRepository {

    var listPost: List<Post>? = null
    var listPhoto: List<Photo>? = null
    var listUser: List<User>? = null

    // loading posts
    fun loadPosts(): LiveData<List<Post>?> {
        val data =  MutableLiveData<List<Post>?>()
        listPost = ArrayList()
        val api = ApiClient.apiService
        val call = api.loadPosts()

        call!!.enqueue(object : Callback<List<Post>?> {
            override fun onResponse(call: Call<List<Post>?>, response: Response<List<Post>?>) {
                if (response.isSuccessful) {
                    data.value = response.body()
                    if (response.body() != null) {
                        listPost = response.body()
                    }
                } else {
                    data.value = null
                }
            }

            override fun onFailure(call: Call<List<Post>?>, t: Throwable) {
                data.value = null
            }
        })
        return data
    }

    // loading photos
    fun loadPhotos(): LiveData<List<Photo>?> {
        val data =  MutableLiveData<List<Photo>?>()
        listPhoto = ArrayList()
        val api = ApiClient.apiService
        val call = api.loadPhotos()

        call!!.enqueue(object : Callback<List<Photo>?> {
            override fun onResponse(call: Call<List<Photo>?>, response: Response<List<Photo>?>) {
                if (response.isSuccessful) {
                    data.value = response.body()
                    if (response.body() != null) {
                        listPhoto = response.body()
                    }
                } else {
                    data.value = null
                }
            }

            override fun onFailure(call: Call<List<Photo>?>, t: Throwable) {
                data.value = null
            }
        })
        return data
    }

    // loading users
    fun loadUsers(): LiveData<List<User>?> {
        val data =  MutableLiveData<List<User>?>()
        listUser = ArrayList()
        val api = ApiClient.apiService
        val call = api.loadUsers()

        call!!.enqueue(object : Callback<List<User>?> {
            override fun onResponse(call: Call<List<User>?>, response: Response<List<User>?>) {
                if (response.isSuccessful) {
                    data.value = response.body()
                    if (response.body() != null) {
                        listUser = response.body()
                    }
                } else {
                    data.value = null
                }
            }

            override fun onFailure(call: Call<List<User>?>, t: Throwable) {
                data.value = null
            }
        })
        return data
    }
}