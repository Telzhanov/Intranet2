package intranet.views

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.user.intranet2.R
import intranet.adapters.ShowAllUsersAdapter
import intranet.models.Student
import intranet.models.Teacher
import intranet.presenters.AllUsersFragmentPresenter
import kotlinx.android.synthetic.main.fragment_all_user.*


class AllUsersFragment : Fragment(), UserListView,AddStudentRefresh {
    override fun addStudentToList(student: Student) {
        dataset.add(student)
    }
    var dataset= ArrayList<Any>()
    lateinit var allUserFragmentPresenter : AllUsersFragmentPresenter
    override fun startloading() {
//        allUserProgressBar.visibility=View.VISIBLE
    }

    override fun stopLoading() {
//        allUserProgressBar.visibility= View.INVISIBLE
    }

    override fun addStudent(students: ArrayList<Student>) {
        dataset?.add(STUDENTS)
        for (st in students) {
            dataset?.add(st)
        }
    }
    override fun addTeacher(teachers: ArrayList<Teacher>) {
        dataset?.add(TEACHERS)
        for (t in teachers) {
            dataset?.add(t)
        }
        Log.d("Dataset",dataset.toString())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_all_user, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        allUserList.layoutManager= LinearLayoutManager(context)
        allUserList.adapter= ShowAllUsersAdapter(dataset,context!!)
    }
    override fun onResume() {
        super.onResume()
        allUserList.adapter.notifyDataSetChanged()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        allUserFragmentPresenter = AllUsersFragmentPresenter(this)
//        allUserFragmentPresenter.loadStudents()
//        allUserFragmentPresenter.loadTeachers()

    }
}
const val STUDENTS="STUDENTS"
const val TEACHERS = "TEACHERS"

