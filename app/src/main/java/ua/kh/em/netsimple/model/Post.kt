package ua.kh.em.netsimple.model

import com.google.gson.annotations.SerializedName

class Post {

    @SerializedName("title")
    var title: String? = null

    @SerializedName("body")
    var body: String? = null
}