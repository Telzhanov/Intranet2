package intranet.presenters

import intranet.App
import intranet.MainContract
import intranet.models.TeacherDb
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class AddTeacherPresenter():MainContract.Presenter{
    override fun onStart(v: MainContract.View) {

    }

    override fun onFinish() {

    }

    fun addTeacher(id: Int, name: String, faculty: String,password: String) {
        val teacher = TeacherDb(id, name, faculty,password)
        Single.fromCallable {
            App.database?.teacherDao()?.insert(teacher)
        }.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe()
    }
}