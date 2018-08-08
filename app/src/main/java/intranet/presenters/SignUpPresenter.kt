package intranet.presenters

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import intranet.MainContract
import intranet.models.Student
import intranet.models.Teacher
import intranet.views.STUDENT
import intranet.views.TEACHER

class SignUpPresenter: MainContract.Presenter{
    private var mAuth: FirebaseAuth? = null
    var database = FirebaseDatabase.getInstance()
    override fun onStart(v: MainContract.View) {
        mAuth = FirebaseAuth.getInstance()
    }

    override fun onFinish() {

    }
    fun signUpUser(email:String, password:String,typeUser:String,name:String){
        mAuth?.createUserWithEmailAndPassword(email,password)
                ?.addOnCompleteListener{
                    if (it.isSuccessful){
                        when(typeUser){
                            TEACHER->{
                                var teacher = Teacher(name,email,password,mAuth?.currentUser?.uid!!)
                                database.getReference("Teachers")
                                        .child(mAuth?.currentUser?.uid)
                                        .setValue(teacher).addOnCompleteListener{
                                            if (it.isSuccessful){

                                            }
                                            else{

                                            }
                                        }
                            }
                            STUDENT->{
                                var student = Student(name, email, password,mAuth?.currentUser?.uid!!)
                                database.getReference("Students")
                                        .child(mAuth?.currentUser?.uid)
                                        .setValue(student).addOnCompleteListener{
                                            if (it.isSuccessful){

                                            }
                                            else{

                                            }
                                        }
                            }
                        }

                    }
                    else{

                    }
                }
    }

}