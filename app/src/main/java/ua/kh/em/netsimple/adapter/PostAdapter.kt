package ua.kh.em.netsimple.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ua.kh.em.netsimple.R
import ua.kh.em.netsimple.adapter.PostAdapter.PostViewHolder
import ua.kh.em.netsimple.model.Post

class PostAdapter(
    private val context: Context,
    private var postList: List<Post>?
) : RecyclerView.Adapter<PostViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val view = LayoutInflater.from(context).inflate(
            R.layout.item_post, parent,
            false
        )
        return PostViewHolder(view)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val item = postList!![position]
        holder.postTitle.text = item.title
        holder.postBody.text = item.body
    }

    override fun getItemCount(): Int {
        return if (postList == null) 0 else postList!!.size
    }

    class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var postTitle: TextView = itemView.findViewById(R.id.post_title)
        var postBody: TextView = itemView.findViewById(R.id.post_body)
    }

    fun addListPosts(postList: List<Post>?) {
        this.postList = postList
        notifyDataSetChanged()
    }
}