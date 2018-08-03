package intranet.views

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.user.intranet2.R
import intranet.adapters.ShowAllUsersAdapter
import intranet.models.Student
import intranet.presenters.StudentFragmentPresenter
import kotlinx.android.synthetic.main.fragment_students.*

class StudentsFragment : Fragment(),StudentListView {
    var dataset = ArrayList<Any>()
    lateinit var studentFragmentPresenter: StudentFragmentPresenter
    override fun addStudent(students: ArrayList<Student>) {
        dataset.add(STUDENTS)
        for (st in students) {
            dataset.add(st)
        }
    }

    override fun startloading() {

    }

    override fun stopLoading() {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_students, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        studentFragmentPresenter = StudentFragmentPresenter(this)
//        studentFragmentPresenter.loadStudents()
    }

    override fun onDetach() {
        super.onDetach()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        studentListView.layoutManager = LinearLayoutManager(context)
        studentListView.adapter = ShowAllUsersAdapter(dataset,context!!)
    }

    override fun onResume() {
        super.onResume()
        studentListView.adapter.notifyDataSetChanged()
    }
}
