package intranet.presenters

import intranet.App
import intranet.MainContract
import intranet.models.StudentDb
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class AddStudentPresenter():MainContract.Presenter{
    override fun onStart(v: MainContract.View) {

    }

    override fun onFinish() {

    }

    fun addStudent(id: Int, name: String, gpa: Double) {
        val student = StudentDb(id, name, gpa)
        Single.fromCallable {
            App.database?.studentDao()?.insert(student)
        }.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe()
    }
}