package ua.kh.em.netkit.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.FrameLayout
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import ua.kh.em.netkit.R


class MainActivity : AppCompatActivity() {
    private var toolbar: Toolbar? = null
    private var fragmentPosts: FrameLayout? = null
    private var bottomNav: BottomNavigationView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupToolbar()
        initViews()
        updateFragment(PostFragment())
        handleFragmentNav()
    }

    private fun setupToolbar() {
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
    }

    private fun initViews() {
        fragmentPosts = findViewById(R.id.fragment_posts)
        bottomNav = findViewById(R.id.bottom_nav)
    }

    private fun handleFragmentNav() {
        bottomNav!!.setOnNavigationItemSelectedListener { item: MenuItem ->
            when (item.itemId) {
                R.id.bn_item_post -> updateFragment(PostFragment())
                R.id.bn_item_photo -> {
                    fragmentPosts!!.visibility = View.GONE
                    updateFragment(PhotoFragment.newInstance())
                }
                R.id.bn_item_user -> {
                    fragmentPosts!!.visibility = View.GONE
                    updateFragment(UserFragment.newInstance())
                }
            }
            true
        }
    }

    private fun updateFragment(fragment: Fragment) {
        val fm = supportFragmentManager
        fm.beginTransaction().replace(R.id.bnav_frame_container, fragment).commit()
    }
}