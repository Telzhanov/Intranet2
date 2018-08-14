package intranet.models

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import intranet.presenters.ChooseCourseBasePresenter
import org.koin.standalone.KoinComponent

class CourseRepoImpl(private val chooseCourseListener:ChooseCourseBasePresenter):CoursesRepo,KoinComponent{
    var database = FirebaseDatabase.getInstance()
    var mAuth = FirebaseAuth.getInstance()
    val coursesListener = object: ValueEventListener{
        override fun onCancelled(p0: DatabaseError?) {}
        override fun onDataChange(p0: DataSnapshot?) {
            for (it in p0?.children!!){
                var course = it.getValue(Course::class.java)
                chooseCourseListener.addCourse(course!!)
                var teacherById = database.getReference("Teachers")
                teacherById.addValueEventListener(object: ValueEventListener {
                    override fun onCancelled(p2: DatabaseError?) {
                    }
                    override fun onDataChange(p2: DataSnapshot?) {
                        for (it3 in p2?.children!!){
                            var teacher = it3.getValue(Teacher::class.java)
                            if (course?.teacherId.equals(teacher?.id)){
                                chooseCourseListener.addTeacher(teacher!!)
                            }
                        }
                    }
                })
            }
        }
    }

    override fun getCoursesFromDb() {
        database.getReference("Courses").removeEventListener(coursesListener)
        database.getReference("Courses").addValueEventListener(coursesListener)
    }
    override fun createCourse(course: Course) {
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


