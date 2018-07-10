package intranet.views

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBar
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import com.example.user.intranet2.R
import intranet.adapters.OpenProfileActivity
import intranet.adapters.UsersPagesAdapter
import intranet.models.Student
import intranet.presenters.UserPresenter
import kotlinx.android.synthetic.main.activity_user.*

class UserActivity : AppCompatActivity(), OpenProfileActivity {
    override fun openProfile(student : Student) {
        var intent= Intent (this, ProfileActivity::class.java)
        intent.putExtra("object",student)
        startActivity(intent)
    }

    var userPresenter: UserPresenter
    init {
        userPresenter= UserPresenter()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)
        setSupportActionBar(toolbar)
        var actionBar: ActionBar? = supportActionBar
        actionBar?.title="Main Menu"
        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar?.setHomeAsUpIndicator(R.drawable.ic_menu)
        val pagerAdapter = UsersPagesAdapter(supportFragmentManager)
        viewPager.adapter=pagerAdapter
        tabLayout.setupWithViewPager(viewPager)
        floatingButton.visibility=View.INVISIBLE
        tabLayout.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener{
            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                when(tab?.position){
                    0->floatingButton.visibility= View.INVISIBLE
                    else->floatingButton.visibility=View.VISIBLE
                }
            }
        })
        floatingButton.setOnClickListener{
            when(tabLayout.selectedTabPosition){

                1->{
                   val intent = Intent(this,AddTeacherActivity::class.java)
                    startActivity(intent)
                }
                2->{
                    val intent = Intent(this,AddStudentActivity::class.java)
                    startActivity(intent)
                }
            }
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




}


