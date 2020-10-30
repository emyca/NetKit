package ua.kh.em.netsimple.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ua.kh.em.netsimple.R
import ua.kh.em.netsimple.ui.adapter.UserAdapter.UserViewHolder
import ua.kh.em.netsimple.data.model.User


class UserAdapter(
    private val context: Context,
    private var userList: List<User>?
) : RecyclerView.Adapter<UserViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_user, parent,
            false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val item = userList!![position]
        holder.userName.text = item.name
        holder.userEmail.text = item.email
        holder.userPhone.text = item.phone
    }

    override fun getItemCount(): Int {
        return if (userList == null) 0 else userList!!.size
    }

    class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var userName: TextView = itemView.findViewById(R.id.user_name)
        var userEmail: TextView = itemView.findViewById(R.id.user_email)
        var userPhone: TextView = itemView.findViewById(R.id.user_phone)
    }

    fun addListUsers(userList: List<User>?) {
        this.userList = userList
        notifyDataSetChanged()
    }
}