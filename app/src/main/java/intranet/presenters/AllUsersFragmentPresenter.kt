package intranet.presenters


import intranet.MainContract
import intranet.views.UserListView

class AllUsersFragmentPresenter(v: MainContract.View):MainContract.Presenter{
    var userListView: UserListView
    init{
        userListView = v as UserListView
    }

    override fun onStart(v: MainContract.View) {

    }

    override fun onFinish() {

    }

//    fun loadStudents() {
//        var students  = ArrayList<Student>()
//        App.database?.studentDao()?.getAllStudents()
//                ?.subscribeOn(Schedulers.io())
//                ?.observeOn(AndroidSchedulers.mainThread())
//                ?.doOnSubscribe { userListView.startloading() }
//                ?.doFinally{userListView.stopLoading()}
//                ?.subscribe { listOfStudents ->
//                    for (student in listOfStudents) {
//                        var s = Student(student.name, student.id,"", student.gpa)
//                        students.add(s)
//                    }
//                    userListView.addStudent(students)
//                }
//    }
//    fun loadTeachers(){
//        var teachers =  ArrayList<Teacher>()
//        App.database?.teacherDao()?.getAllTeacher()
//                ?.subscribeOn(Schedulers.io())
//                ?.observeOn(AndroidSchedulers.mainThread())
//                ?.doOnSubscribe { userListView.startloading() }
//                ?.doFinally{userListView.stopLoading()}
//                ?.subscribe { listOfTeachers ->
//                    for (teacher in listOfTeachers) {
//                        var t = Teacher(teacher.name, teacher.id, "",teacher.faculty)
//                        teachers.add(t)
//                    }
//                    userListView.addTeacher(teachers)
//                }
//    }
}