package intranet.presenters

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import intranet.adapters.ShowAllUsersAdapter
import intranet.fragments.HEADER
import intranet.models.Student
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class UserPresenter(context: Context):BasePresenter(context){


    fun showUsers(recyclerView: RecyclerView) {
        var dataset=ArrayList<Any>()
        Data.database?.studentDao()?.getAllStudents()
                ?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe { listOfStudents ->
                    dataset.add(HEADER)
                    for (student in listOfStudents) {
                        var s = Student(student.name, student.id, student.gpa)
                        dataset.add(s)
                    }
                    recyclerView.layoutManager= LinearLayoutManager(context)
                    recyclerView.adapter=ShowAllUsersAdapter(dataset)
                    recyclerView.adapter.notifyDataSetChanged()
                }
    }

}
