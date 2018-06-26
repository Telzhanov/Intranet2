package intranet.presenters

import android.arch.persistence.room.Room
import android.content.Context
import android.util.Log
import intranet.models.MyDatabase
import intranet.models.Student
import intranet.models.StudentDb
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

open class BasePresenter(var context : Context){
    init{
        Data.database = Room.databaseBuilder(context, MyDatabase::class.java, "MyDB").build()
    }
    fun registerAllStudentListener() {
        Data.database?.studentDao()?.getAllStudents()
                ?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe { listOfStudents ->
                    Log.d("ashat", listOfStudents.toString())
                    Data.students = ArrayList<Student>()
                    for (student in listOfStudents) {
                        var s = Student(student.name, student.id, student.gpa)
                        Data.students.add(s)
                    }
                    Log.d("students", Data.students.size.toString())
                }
    }
    fun addStudent(id: Int, name: String, gpa: Double) {
        val student = StudentDb(id, name, gpa)
        Single.fromCallable {
            Data.database?.studentDao()?.insert(student)
        }.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe()
    }
}