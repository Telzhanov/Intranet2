package intranet.views

import android.os.Bundle
import android.support.v7.app.ActionBar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import com.example.user.intranet2.R
import intranet.adapters.MarkListAdapter
import intranet.models.Course
import intranet.models.StudentsMarks
import intranet.presenters.StudentMarkPresenter
import kotlinx.android.synthetic.main.activity_student_mark.*

class StudentMarkActivity : AppCompatActivity(),StudentMarkView{
    var marks = ArrayList<StudentsMarks>()
    override fun setStudentsMarkFromDb(marks: ArrayList<StudentsMarks>) {
        this.marks = marks
    }

    override fun showListMarks() {
        markList.layoutManager = GridLayoutManager(this,2)
        markList.adapter = MarkListAdapter(marks)
    }

    var studentMarkPresenter = StudentMarkPresenter()
    lateinit var course: Course
    override fun startloading() {

    }

    override fun stopLoading() {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_mark)
        var intent = getIntent()
        course = intent.getSerializableExtra("course") as Course
        setSupportActionBar(toolbarListMark)
        var actionBar: ActionBar? = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
        studentMarkPresenter.onStart(this)
        studentMarkPresenter.loadMarks(course)
        actionBar?.title=course.name
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
