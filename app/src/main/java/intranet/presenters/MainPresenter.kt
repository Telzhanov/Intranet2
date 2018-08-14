package intranet.presenters

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.FirebaseDatabase
import intranet.MainContract
import intranet.views.LoginView


class MainPresenter():MainContract.Presenter {
    //    var allUsers = ArrayList<Person>()
    lateinit var loginView : LoginView
    private var mAuth: FirebaseAuth? = null
    var database = FirebaseDatabase.getInstance()
    override fun onStart(v: MainContract.View) {
        loginView = v as LoginView
        mAuth = FirebaseAuth.getInstance()
//        var ref = database.getReference("Courses").push()
//        var course1 = Course(ref.key.toString(),"Calculus",3)
//        ref.setValue(course1).addOnCompleteListener{
//            if (it.isSuccessful){
//                Log.d("Courses",course1.toString())
//            }
//            else{
//                Log.d("exceptionCourse",it.exception?.message)
//            }
//        }
        if (mAuth?.currentUser !=null){
            loginView.openList()
        }
//        var users = ArrayList<Person>()
//        var userList:Flowable<List<Person>> = Flowable.zip(
//                App.database?.teacherDao()?.getAllTeacher(),
//                App.database?.studentDao()?.getAllStudents(),
//                BiFunction { t1, t2 ->
//                    for(i in t1){
//                        var user = Person(i.name,i.id,i.password)
//                        users.add(user)
//                    }
//                    for (i in t2){
//                        var user = Person(i.name,i.id,i.password)
//                        users.add(user)
//                    }
//                    return@BiFunction users
//                }
//        )
//        userList.subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe{
//                    for (person in it){
//                        allUsers.add(person)
//                    }
//                }
    }
    override fun onFinish() {

    }
    fun checkUser(email:String, password:String){
        mAuth?.signInWithEmailAndPassword(email,password)?.addOnCompleteListener{
            if (it.isSuccessful){
                var user: FirebaseUser? = mAuth?.currentUser
                loginView.openList()
            }
            else{
                Log.d("Owibka",it.exception?.message)
            }
        }
    }

//    fun checkUser(id: Int,password: String): Boolean {
//        var isFound :Boolean = false
//        for (user in allUsers){
//            var person : Person = user as Person
//            if (person.id == id && person.password == password){
//                isFound=true
//            }
//        }
//        return isFound
//    }
}
