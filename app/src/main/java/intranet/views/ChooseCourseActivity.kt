package intranet.views

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.example.user.intranet2.R
import intranet.adapters.ChooseListCourseAdapter
import intranet.models.Course
import intranet.models.Teacher
import intranet.presenters.ChooseCoursePresenter
import kotlinx.android.synthetic.main.activity_choose_course.*
import org.koin.android.ext.android.inject

class ChooseCourseActivity : AppCompatActivity(),ChooseCourseView,ChooseListAdapterActivity {
    val chooseCoursePresenter:ChooseCoursePresenter by inject()
    override fun callToast() {
        Toast.makeText(this, "You have this course!", Toast.LENGTH_SHORT).show()
    }

    override fun createCrouse(course: Course) {
        chooseCoursePresenter.createCourse(course)
    }

    override fun close() {
        finish()
    }
    override fun showCourseList(courses:ArrayList<Course>,teachers:ArrayList<Teacher>) {
        chooseCourseList.layoutManager = LinearLayoutManager(this)
        chooseCourseList.adapter = ChooseListCourseAdapter(courses,this,teachers)
    }

    override fun startloading() {

    }

    override fun stopLoading() {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_course)
        chooseCoursePresenter.onStart(this)
        chooseCoursePresenter.loadCourses()
    }
}
