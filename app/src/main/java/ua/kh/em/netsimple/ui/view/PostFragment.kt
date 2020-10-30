package ua.kh.em.netsimple.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import ua.kh.em.netsimple.R
import ua.kh.em.netsimple.ui.adapter.PostAdapter
import ua.kh.em.netsimple.data.model.Post
import ua.kh.em.netsimple.utils.CheckNet.isNetExists
import ua.kh.em.netsimple.ui.viewmodel.PostViewModel


class PostFragment : Fragment() {

    private var recyclerView: RecyclerView? = null
    private val postList: List<Post> = ArrayList()
    private var layoutManager: LinearLayoutManager? = null
    private var adapter: PostAdapter? = null
    private var progressBar: ProgressBar? = null
    private lateinit var viewModel: PostViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_post, container,false)
        initViews()
        if (isNetExists(context)) {
            setupRecyclerView(view)
            loadPostsNet()
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
        recyclerView = view.findViewById(R.id.list_posts)
        if (adapter == null) {
            adapter = PostAdapter(requireActivity(), postList)
            layoutManager = LinearLayoutManager(requireActivity())
            recyclerView?.layoutManager = layoutManager

            // divider line between RecyclerView items
            val dividerItemDecoration: ItemDecoration = DividerItemDecoration(
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

    private fun loadPostsNet() {
        viewModel = ViewModelProvider(requireActivity()).get(PostViewModel::class.java)
        viewModel.loadPosts().observe(requireActivity(), { posts: List<Post>? ->
            if (posts != null) {
                progressBar?.visibility = View.GONE
                adapter?.addListPosts(posts)
            }else if (postList.isEmpty()){
                progressBar?.visibility = View.GONE
                Toast.makeText(context, R.string.something_wrong, Toast.LENGTH_LONG).show()
            }
        })
    }
}