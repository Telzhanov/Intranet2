package intranet.activities

import android.content.Intent
import android.os.Bundle
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBar
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import com.example.user.intranet2.R
import com.example.user.intranet2.R.id.*
import intranet.adapters.MyPagerAdapter
import intranet.presenters.UserPresenter
import kotlinx.android.synthetic.main.activity_user.*

class UserActivity : AppCompatActivity(), StudentFragmentListener{
    var userPresenter: UserPresenter
    init {
        userPresenter= UserPresenter(this)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)
        setSupportActionBar(toolbar)
        var actionBar: ActionBar? = supportActionBar
        actionBar?.title="Main Menu"
        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar?.setHomeAsUpIndicator(R.drawable.ic_menu)
        val pagerAdapter = MyPagerAdapter(supportFragmentManager)
        viewPager.adapter=pagerAdapter
        tabLayout.setupWithViewPager(viewPager)
        floatingButton.setOnClickListener{
            val intent = Intent(this, AddStudentActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        var menuInflater: MenuInflater = menuInflater
        menuInflater.inflate(R.menu.app_menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            android.R.id.home -> {
                mDrawerLayout.openDrawer(GravityCompat.START)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun fetchData() {
        userPresenter.registerAllStudentListener()
    }
}
interface StudentFragmentListener{
    fun fetchData()
}
