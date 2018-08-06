package intranet.views

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.example.user.intranet2.R
import intranet.adapters.ChooseListCourseAdapter
import intranet.models.Course
import intranet.presenters.ChooseCoursePresenter
import kotlinx.android.synthetic.main.activity_choose_course.*

class ChooseCourseActivity : AppCompatActivity(),ChooseCourseView,ChooseListAdapterActivity {
    override fun close() {
        finish()
    }
    var courses = ArrayList<Course>()
    var chooseCoursePresenter = ChooseCoursePresenter()
    override fun setCourseFromDb(courses: ArrayList<Course>) {
        this.courses = courses
    }

    override fun showCourseList() {
        chooseCourseList.layoutManager = LinearLayoutManager(this)
        chooseCourseList.adapter = ChooseListCourseAdapter(courses,this)
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
