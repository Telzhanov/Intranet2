package intranet.presenters

import intranet.MainContract
import intranet.views.StudentListView

class StudentFragmentPresenter(v : MainContract.View):MainContract.Presenter{
    override fun onStart(v: MainContract.View) {

    }

    override fun onFinish() {

    }

    var studentListView: StudentListView
    init{
        studentListView = v as StudentListView
    }
//    fun loadStudents() {
//        var students  = ArrayList<Student>()
//        App.database?.studentDao()?.getAllStudents()
//                ?.subscribeOn(Schedulers.io())
//                ?.doOnSubscribe { studentListView.startloading() }
//                ?.doFinally{studentListView.stopLoading()}
//                ?.observeOn(AndroidSchedulers.mainThread())
//                ?.subscribe { listOfStudents ->
//                    for (student in listOfStudents) {
//                        var s = Student(student.name, student.id, "",student.gpa)
//                        students.add(s)
//                    }
//                    studentListView.addStudent(students)
//                }
//    }
}