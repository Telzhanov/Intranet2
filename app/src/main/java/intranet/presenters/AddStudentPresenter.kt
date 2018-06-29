package intranet.presenters

import android.content.Context
import intranet.models.StudentDb
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class AddStudentPresenter(context: Context):BasePresenter(context){
    fun addStudent(id: Int, name: String, gpa: Double) {
        val student = StudentDb(id, name, gpa)
        Single.fromCallable {
            Data.database?.studentDao()?.insert(student)
        }.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe()
    }
}