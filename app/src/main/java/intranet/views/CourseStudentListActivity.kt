package intranet.views

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.support.v7.app.ActionBar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import com.example.user.intranet2.R
import intranet.adapters.StudentListAdapter
import intranet.models.Course
import intranet.models.Student
import intranet.presenters.CourseStudentListPresenter
import kotlinx.android.synthetic.main.activity_course_student_list.*

class CourseStudentListActivity : AppCompatActivity(),CourseStudentListView,CourseStudentListAdapterActivity {
    override fun showDialog(studentId: String) {
        var dialog: AlertDialog.Builder = AlertDialog.Builder(this)
        var  mView : View = layoutInflater.inflate(R.layout.put_mark_dialog,null)
        var rate:EditText = mView.findViewById(R.id.markPoint)
        dialog.setCancelable(true)
        dialog.setNegativeButton("Cancel",object: DialogInterface.OnClickListener{
            override fun onClick(dialog: DialogInterface?, which: Int) {
                dialog?.cancel()
            }

        })
        dialog.setPositiveButton("Put",object: DialogInterface.OnClickListener{
            override fun onClick(dialog: DialogInterface?, which: Int) {
                courseStudentListPresenter.createMarkForStudent(rate.text.toString().toInt(),studentId)
            }

        })
        dialog.setView(mView)
        var alertDialog = dialog.create()
        alertDialog.show()
    }

    override fun putMark(mark: Int,studentId:String) {
        courseStudentListPresenter.createMarkForStudent(mark,studentId)
    }

    var students = ArrayList<Student>()
    var courseStudentListPresenter = CourseStudentListPresenter()
    lateinit var course:Course
    override fun setStudentsFromDb(students: ArrayList<Student>) {
        Log.d("ListStudents", students.toString())
        this.students = students
    }

    override fun showStudentList() {
        studentList.layoutManager = LinearLayoutManager(this)
        studentList.adapter = StudentListAdapter(students,this)
    }

    override fun startloading() {

    }

    override fun stopLoading() {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_course_student_list)
        var intent = getIntent()
        course = intent.getSerializableExtra("course") as Course
        setSupportActionBar(toolbarListStudent)
        var actionBar: ActionBar? = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar?.title=course.name
        courseStudentListPresenter.onStart(this)
        courseStudentListPresenter.loadStudents(course)
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        var menuInflater: MenuInflater = menuInflater
        menuInflater.inflate(R.menu.app_menu,menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
//            R.id.signOutButton->{
//                teacherMenuPresenter.signOut()
//                val intent = Intent(this, MainActivity::class.java)
//                startActivity(intent)
//                finish()
//                true
//            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
