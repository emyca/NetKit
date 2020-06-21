package ua.kh.em.netsimple.network

import retrofit2.Call
import retrofit2.http.GET
import ua.kh.em.netsimple.model.Photo
import ua.kh.em.netsimple.model.Post
import ua.kh.em.netsimple.model.User

interface ApiService {

    @GET("posts")
    fun loadPosts(): Call<List<Post>?>?

    @GET("photos")
    fun loadPhotos(): Call<List<Photo>?>?

    @GET("users")
    fun loadUsers(): Call<List<User>?>?
}