package intranet.views

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.ActionBar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import com.example.user.intranet2.R
import intranet.adapters.DisciplineListTeacherAdapter
import intranet.models.Course
import intranet.presenters.TeacherMenuPresenter
import kotlinx.android.synthetic.main.activity_teacher_menu.*

class TeacherMenuActivity : AppCompatActivity(),TeacherMenuView {
    override fun setTypeUserMenu(typeUser: String) {
        this.typeUser=typeUser
    }

    var courses= ArrayList<Course>()
    var typeUser:String?=null
    var teacherMenuPresenter =TeacherMenuPresenter()
//    override fun addCourseFromDb(course: Course) {
//        Log.d("AddingCours",course.toString())
//        courses?.add(course)
//        Log.d("ListCourses",courses.toString())
//    }
    override fun setCoursesFromDb(courses: ArrayList<Course>) {
       this.courses = courses
    }
    override fun showCoursesList() {
        Log.d("ListCourses2",courses.toString())
        disciplineList.layoutManager= LinearLayoutManager(this)
        disciplineList.adapter= DisciplineListTeacherAdapter(courses!!)
    }
    override fun startloading() {

    }

    override fun stopLoading() {

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        teacherMenuPresenter.onStart(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_teacher_menu)
        setSupportActionBar(toolbarTM)
        var actionBar: ActionBar? = supportActionBar
        actionBar?.title="Main Menu"
        addCourseButton.setOnClickListener {
            when(typeUser){
                intranet.presenters.TEACHER->{
                    val intent = Intent(this,AddCourseActivity::class.java)
                    startActivity(intent)
                }
                intranet.presenters.STUDENT->{
                    val intent = Intent(this,ChooseCourseActivity::class.java)
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
//            android.R.id.home -> {
//                mDrawerLayout.openDrawer(GravityCompat.START)
//                true
//            }
            R.id.signOutButton->{
                teacherMenuPresenter.signOut()
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}
