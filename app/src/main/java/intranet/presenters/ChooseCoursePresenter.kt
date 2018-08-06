package intranet.presenters

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import intranet.MainContract
import intranet.models.Course
import intranet.models.StudentCourses
import intranet.views.ChooseCourseView

class ChooseCoursePresenter:MainContract.Presenter{
    lateinit var chooseCourseView: ChooseCourseView
    private var mAuth = FirebaseAuth.getInstance()
    var database = FirebaseDatabase.getInstance()
    override fun onStart(v: MainContract.View) {
        chooseCourseView = v as ChooseCourseView
    }

    override fun onFinish() {

    }
    fun loadCourses(){
        var courseList = database.getReference("Courses").addValueEventListener(object : ValueEventListener{
            override fun onCancelled(p0: DatabaseError?) {

            }

            override fun onDataChange(p0: DataSnapshot?) {
                var courses = ArrayList<Course>()
                for (it in p0?.children!!){
                    var course = it.getValue(Course::class.java)
                    courses.add(course!!)
                }
                chooseCourseView.setCourseFromDb(courses)
                chooseCourseView.showCourseList()
            }

        })
    }
    fun createCourse(course:Course){
        var itemCourse = database.getReference("StudentCourses").push()
        var studentCourse = StudentCourses(mAuth.currentUser?.uid!!,course.id!!)
        itemCourse.setValue(studentCourse).addOnCompleteListener{
            if (it.isSuccessful){

            }
            else{

            }
        }
    }

}