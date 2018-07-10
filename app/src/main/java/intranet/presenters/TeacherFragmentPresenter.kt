package intranet.presenters

import intranet.App
import intranet.MainContract
import intranet.models.Teacher
import intranet.views.TeacherListView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class TeacherFragmentPresenter(v : MainContract.View):MainContract.Presenter{
    var teacherListView: TeacherListView
    init{
        teacherListView = v as TeacherListView
    }

    override fun onStart(v: MainContract.View) {

    }

    override fun onFinish() {

    }

    fun loadTeachers(){
        var teachers =  ArrayList<Teacher>()
        App.database?.teacherDao()?.getAllTeacher()
                ?.subscribeOn(Schedulers.io())
                ?.doOnSubscribe { teacherListView.startloading() }
                ?.doFinally{teacherListView.stopLoading()}
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe { listOfTeachers ->
                    for (teacher in listOfTeachers) {
                        var t = Teacher(teacher.name, teacher.id, teacher.faculty)
                        teachers.add(t)
                    }
                    teacherListView.addTeacher(teachers)
                }
    }
}