package ua.kh.em.netsimple.model

import com.google.gson.annotations.SerializedName

class Photo {

    @SerializedName("title")
    var title: String? = null

    @SerializedName("url")
    var image: String? = null
}