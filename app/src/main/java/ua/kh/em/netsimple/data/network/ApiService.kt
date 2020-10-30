package ua.kh.em.netsimple.data.network

import retrofit2.Call
import retrofit2.http.GET
import ua.kh.em.netsimple.data.model.Photo
import ua.kh.em.netsimple.data.model.Post
import ua.kh.em.netsimple.data.model.User

interface ApiService {

    @GET("posts")
    fun loadPosts(): Call<List<Post>?>?

    @GET("photos")
    fun loadPhotos(): Call<List<Photo>?>?

    @GET("users")
    fun loadUsers(): Call<List<User>?>?
}