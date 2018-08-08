package intranet.views

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.ActionBar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import com.example.user.intranet2.R
import intranet.adapters.DisciplineListTeacherAdapter
import intranet.models.Course
import intranet.models.Person
import intranet.models.Teacher
import intranet.presenters.TeacherMenuPresenter
import kotlinx.android.synthetic.main.activity_teacher_menu.*

class TeacherMenuActivity : AppCompatActivity(),TeacherMenuView,TeacherMenuAdapterActivity{
    override fun setGpaFromDb(gpa: Double) {
        this.gpa = gpa
    }

    override fun setUserFromDb(user: Person) {
        profileName.text = user.name
    }
    var courses= ArrayList<Course>()
    var typeUser:String?=null
    var teacherMenuPresenter =TeacherMenuPresenter()
    var teachers = ArrayList<Teacher>()
    var gpa = 0.0
    override fun setTeachersFromDb(teachers: ArrayList<Teacher>) {
        this.teachers=teachers
    }

    override fun showMarksStudents(course:Course) {
        val intent = Intent(this,StudentMarkActivity::class.java)
        intent.putExtra("course",course)
        startActivity(intent)
    }

    override fun showListStudents(course:Course) {
        val intent = Intent(this,CourseStudentListActivity::class.java)
        intent.putExtra("course",course)
        startActivity(intent)
    }

    override fun setTypeUserMenu(typeUser: String) {
        this.typeUser=typeUser
    }
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
        disciplineList.adapter= DisciplineListTeacherAdapter(courses!!,this,typeUser!!,teachers)
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
        actionBar?.setDisplayHomeAsUpEnabled(true)
        teacherMenuPresenter.calculateGpa()
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
        when(typeUser){
            intranet.presenters.TEACHER->{
                menuInflater.inflate(R.menu.teacher_menu,menu)
                return true
            }
            else->{
                menuInflater.inflate(R.menu.student_menu,menu)
                return true
            }
        }
    }
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            R.id.myGpa -> {
                var dialog: AlertDialog.Builder = AlertDialog.Builder(this)
                var  mView : View = layoutInflater.inflate(R.layout.gpa_dialog,null)
                var rate: TextView = mView.findViewById(R.id.gpaText)
                rate.text = gpa.toString()
                dialog.setCancelable(true)
                dialog.setNegativeButton("Cancel",object: DialogInterface.OnClickListener{
                    override fun onClick(dialog: DialogInterface?, which: Int) {
                        dialog?.cancel()
                    }

                })
                dialog.setView(mView)
                var alertDialog = dialog.create()
                alertDialog.show()
                true
            }
            R.id.signOutButton->{
                teacherMenuPresenter.signOut()
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}
