package ua.kh.em.netkit.data.network

import retrofit2.Call
import retrofit2.http.GET
import ua.kh.em.netkit.data.model.Photo
import ua.kh.em.netkit.data.model.Post
import ua.kh.em.netkit.data.model.User

interface ApiService {

    @GET("posts")
    fun loadPosts(): Call<List<Post>?>?

    @GET("photos")
    fun loadPhotos(): Call<List<Photo>?>?

    @GET("users")
    fun loadUsers(): Call<List<User>?>?
}