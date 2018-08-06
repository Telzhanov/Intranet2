package intranet.views

import android.os.Bundle
import android.support.v7.app.ActionBar
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import com.example.user.intranet2.R
import intranet.models.Course
import intranet.presenters.AddCoursePresenter
import kotlinx.android.synthetic.main.activity_add_course.*

class AddCourseActivity : AppCompatActivity(),AddCourseView {
    override fun startloading() {

    }

    override fun stopLoading() {

    }

    var addCoursePresenter = AddCoursePresenter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addCoursePresenter.onStart(this)
        setContentView(R.layout.activity_add_course)
        setSupportActionBar(toolbarAddCourse)
        var actionBar: ActionBar? = supportActionBar
        actionBar?.title="Add Course"
        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar?.setHomeAsUpIndicator(R.drawable.ic_navigate_before_black_24dp)
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        var menuInflater: MenuInflater = menuInflater
        menuInflater.inflate(R.menu.add_student_menu,menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            R.id.idDone->{
                var course = Course(NameCourseEdit.text.toString(),creditsEdit.text.toString().toInt())
                addCoursePresenter.createCourse(course)
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
