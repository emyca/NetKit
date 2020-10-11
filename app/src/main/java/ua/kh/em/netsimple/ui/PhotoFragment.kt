package ua.kh.em.netsimple.ui

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
import ua.kh.em.netsimple.R
import ua.kh.em.netsimple.adapter.PhotoAdapter
import ua.kh.em.netsimple.model.Photo
import ua.kh.em.netsimple.utils.CheckNet
import ua.kh.em.netsimple.viewmodel.PhotoViewModel
import java.util.ArrayList


class PhotoFragment : Fragment() {

    companion object {
        fun newInstance() = PhotoFragment()
    }

    private var recyclerView: RecyclerView? = null
    private val photoList: List<Photo> = ArrayList()
    private var layoutManager: LinearLayoutManager? = null
    private var adapter: PhotoAdapter? = null
    private var progressBar: ProgressBar? = null
    private lateinit var viewModel: PhotoViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_photo, container, false)
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
        recyclerView = view.findViewById(R.id.list_photos)
        if (adapter == null) {
            adapter = PhotoAdapter(requireActivity(), photoList)
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
        viewModel = ViewModelProvider(this).get(PhotoViewModel::class.java)
        viewModel.loadPhotos().observe(requireActivity(), { photos: List<Photo>? ->
            if (photos!= null) {
                progressBar?.visibility = View.GONE
                adapter?.addListPhotos(photos)
            }else if (photoList.isEmpty()) {
                progressBar?.visibility = View.GONE
                Toast.makeText(context, R.string.something_wrong, Toast.LENGTH_LONG).show()
            }
        })
    }
}