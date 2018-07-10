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
import intranet.models.Teacher
import intranet.presenters.TeacherFragmentPresenter
import kotlinx.android.synthetic.main.fragment_teacher.*

class TeacherFragment : Fragment(),TeacherListView {
    var dataset = ArrayList<Any>()
    lateinit var teacherFragmentPresenter: TeacherFragmentPresenter
    override fun addTeacher(teachers: ArrayList<Teacher>) {
        dataset.add(TEACHERS)
        for (t in teachers) {
            dataset.add(t)
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
        return inflater.inflate(R.layout.fragment_teacher, container, false)
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        teacherFragmentPresenter= TeacherFragmentPresenter(this)
        teacherFragmentPresenter.loadTeachers()

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        teacherListView.layoutManager = LinearLayoutManager(context)
        teacherListView.adapter = ShowAllUsersAdapter(dataset, context!!)
        super.onViewCreated(view, savedInstanceState)
    }
    override fun onResume() {
        super.onResume()
        teacherListView.adapter.notifyDataSetChanged()
    }
    override fun onDetach() {
        super.onDetach()
    }
}
