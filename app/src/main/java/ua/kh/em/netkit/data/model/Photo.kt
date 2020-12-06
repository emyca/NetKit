package ua.kh.em.netkit.data.model

import com.google.gson.annotations.SerializedName

class Photo {

    @SerializedName("title")
    var title: String? = null

    @SerializedName("url")
    var image: String? = null
}