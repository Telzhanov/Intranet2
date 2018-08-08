package intranet.presenters

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import intranet.MainContract
import intranet.models.Course
import intranet.models.StudentCourses
import intranet.models.Teacher
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
                var teachers = ArrayList<Teacher>()
                for (it in p0?.children!!){
                    var course = it.getValue(Course::class.java)
                    courses.add(course!!)
                    var teacherById = database.getReference("Teachers")
                    teacherById.addValueEventListener(object:ValueEventListener{
                        override fun onCancelled(p2: DatabaseError?) {

                        }

                        override fun onDataChange(p2: DataSnapshot?) {
                            for (it3 in p2?.children!!){
                                var teacher = it3.getValue(Teacher::class.java)
                                if (course?.teacherId.equals(teacher?.id)){
                                    teachers.add(teacher!!)
                                }
                            }
                            chooseCourseView.setCourseFromDb(courses)
                            chooseCourseView.setTeacherFromDb(teachers)
                            chooseCourseView.showCourseList()
                        }
                })
                }
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