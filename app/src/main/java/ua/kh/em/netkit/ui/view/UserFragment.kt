package ua.kh.em.netkit.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ua.kh.em.netkit.R
import ua.kh.em.netkit.ui.adapter.UserAdapter
import ua.kh.em.netkit.data.model.User
import ua.kh.em.netkit.utils.CheckNet
import ua.kh.em.netkit.ui.viewmodel.UserViewModel
import java.util.ArrayList


class UserFragment : Fragment() {

    companion object {
        fun newInstance() = UserFragment()
    }

    private var recyclerView: RecyclerView? = null
    private val userList: List<User> = ArrayList()
    private var layoutManager: LinearLayoutManager? = null
    private var adapter: UserAdapter? = null
    private var progressBar: ProgressBar? = null
    private lateinit var viewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_user, container,false)
        initViews()
        if (CheckNet.isNetExists(context)) {
            setupRecyclerView(view)
            onActivityCreated(savedInstanceState)
        } else {
            progressBar?.visibility = View.GONE
            Toast.makeText(context, R.string.no_internet, Toast.LENGTH_LONG).show()
        }
        return view
    }

    private fun initViews() {
        progressBar = view?.findViewById(R.id.progressbar)
    }

    private fun setupRecyclerView(view: View) {
        recyclerView = view.findViewById(R.id.list_users)
        if (adapter == null) {
            adapter = UserAdapter(requireActivity(), userList)
            layoutManager = LinearLayoutManager(requireActivity())
            recyclerView?.layoutManager = layoutManager

            // divider line between RecyclerView items
            val dividerItemDecoration: RecyclerView.ItemDecoration = DividerItemDecoration(
                recyclerView?.context,
                layoutManager!!.orientation
            )
            recyclerView?.addItemDecoration(dividerItemDecoration)
            recyclerView?.setHasFixedSize(true)
            recyclerView?.adapter = adapter
        } else {
            adapter?.notifyDataSetChanged()
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        viewModel.loadUsers().observe(requireActivity(), { users: List<User>? ->
            if (users != null) {
                progressBar?.visibility = View.GONE
                adapter?.addListUsers(users)
            }else if (userList.isEmpty()) {
                progressBar?.visibility = View.GONE
                Toast.makeText(context, R.string.something_wrong, Toast.LENGTH_LONG).show()
            }
        })
    }
}