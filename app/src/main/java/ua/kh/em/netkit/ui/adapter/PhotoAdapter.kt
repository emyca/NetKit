package ua.kh.em.netkit.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import ua.kh.em.netkit.R
import ua.kh.em.netkit.ui.adapter.PhotoAdapter.PhotoViewHolder
import ua.kh.em.netkit.data.model.Photo


class PhotoAdapter (
    private val context: Context,
    private var photoList: List<Photo>?
) : RecyclerView.Adapter<PhotoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        return PhotoViewHolder(
            LayoutInflater.from(context).inflate(R.layout.item_photo, parent,
                false)
        )
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        val item = photoList!![position]
        Picasso.get()
            .load(item.image)
            .error(R.drawable.ic_image)
            .placeholder(R.drawable.ic_image)
            .fit()
            .into(holder.photoImg)
        holder.photoTitle.text = item.title

    }

    override fun getItemCount(): Int {
        return photoList!!.size
    }

    class PhotoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var photoImg: ImageView = itemView.findViewById(R.id.photo_image)
        var photoTitle: TextView = itemView.findViewById(R.id.photo_title)
    }

    fun addListPhotos(photoList: List<Photo>?) {
        this.photoList = photoList
        notifyDataSetChanged()
    }
}