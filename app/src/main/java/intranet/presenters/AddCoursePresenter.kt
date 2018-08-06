package intranet.presenters

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import intranet.MainContract
import intranet.models.Course
import intranet.models.TeacherCourses
import intranet.views.AddCourseView

class AddCoursePresenter:MainContract.Presenter{
    var database = FirebaseDatabase.getInstance()
    lateinit var addCourseView :AddCourseView
    private var mAuth = FirebaseAuth.getInstance()
    override fun onStart(v: MainContract.View) {
        addCourseView = v as AddCourseView
    }

    override fun onFinish() {

    }
    fun createCourse(course: Course){
        var itemCourse = database.getReference("Courses").push()
        var course1 = Course(course.name!!,itemCourse.key.toString(),course.credits!!,mAuth.uid!!)
        itemCourse.setValue(course1).addOnCompleteListener{
                        if (it.isSuccessful){
                Log.d("Courses",course1.toString())
            }
            else{
                Log.d("exceptionCourse",it.exception?.message)
            }
        }
        var teacherCourse = TeacherCourses(mAuth.currentUser?.uid!!,course1.id!!)
        var itemTeacherCourse = database.getReference("TeacherCourses").push()
        itemTeacherCourse.setValue(teacherCourse).addOnCompleteListener {
            if (it.isSuccessful){

            }
            else{

            }
        }
    }

}