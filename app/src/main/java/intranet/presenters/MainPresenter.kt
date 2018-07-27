package intranet.presenters

import intranet.App
import intranet.MainContract
import intranet.models.Person
import intranet.views.LoginView
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.BiFunction
import io.reactivex.schedulers.Schedulers

class MainPresenter():MainContract.Presenter {
    var allUsers = ArrayList<Person>()
    lateinit var loginView : LoginView
    override fun onStart(v: MainContract.View) {
        loginView = v as LoginView
        var users = ArrayList<Person>()
        var userList:Flowable<List<Person>> = Flowable.zip(
                App.database?.teacherDao()?.getAllTeacher(),
                App.database?.studentDao()?.getAllStudents(),
                BiFunction { t1, t2 ->
                    for(i in t1){
                        var user = Person(i.name,i.id,i.password)
                        users.add(user)
                    }
                    for (i in t2){
                        var user = Person(i.name,i.id,i.password)
                        users.add(user)
                    }
                    return@BiFunction users
                }
        )
        userList.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe{
                    for (person in it){
                        allUsers.add(person)
                    }
                }
    }

    override fun onFinish() {

    }

    fun checkUser(id: Int,password: String): Boolean {
        var isFound :Boolean = false
        for (user in allUsers){
            var person : Person = user as Person
            if (person.id == id && person.password == password){
                isFound=true
            }
        }
        return isFound
    }
}