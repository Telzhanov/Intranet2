package intranet.presenters

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import intranet.MainContract
import intranet.models.Course
import intranet.models.StudentCourses
import intranet.views.TeacherMenuView


class TeacherMenuPresenter:MainContract.Presenter{
    lateinit var teacherMenuView: TeacherMenuView
    private var mAuth = FirebaseAuth.getInstance()
    var database = FirebaseDatabase.getInstance()
    override fun onStart(v: MainContract.View) {
        var isFound: Boolean=false
        teacherMenuView = v as TeacherMenuView
        var allTeachers = database.getReference("Teachers")
        var allStudents = database.getReference("Students")
        allTeachers.addValueEventListener(object:ValueEventListener{
            override fun onCancelled(p0: DatabaseError?) {

            }

            override fun onDataChange(p0: DataSnapshot?) {
                for (it in p0?.children!!){
                    if (it.key.equals(mAuth.currentUser?.uid)){
                        loadCourses(TEACHER)
                        isFound=true
                        break
                    }
                }
            }
        })
        allStudents.addValueEventListener(object:ValueEventListener{
            override fun onCancelled(p0: DatabaseError?) {

            }
            override fun onDataChange(p0: DataSnapshot?) {
                when(isFound){
                    true->{

                    }
                    false->{
                        for (it in p0?.children!!){
                            if (it.key.equals(mAuth.currentUser?.uid)){
                                loadCourses(STUDENT)
                                break
                            }
                        }
                    }
                }
            }

        })

    }

    override fun onFinish() {

    }
    fun signOut(){
        mAuth.signOut()
    }
    fun addCourse(){

    }
    fun loadCourses(typeUser:String){
        when(typeUser){
            STUDENT->{
                teacherMenuView.setTypeUserMenu(typeUser)
                var myRef = database.getReference("StudentCourses")
//                var courses = ArrayList<Course>()
                myRef.addValueEventListener(object:ValueEventListener{
                    override fun onCancelled(p0: DatabaseError?) {

                    }

                    override fun onDataChange(p0: DataSnapshot?) {
                        var courses = ArrayList<Course>()
                        for (it in p0?.children!!){

                            val courseStudent = it.getValue(StudentCourses::class.java)
                            Log.d("thisId", mAuth.currentUser?.uid)
                            Log.d("CourseLoad", courseStudent?.studentId.equals(mAuth.currentUser?.uid).toString())
                            if (courseStudent?.studentId.equals(mAuth.currentUser?.uid)){
                                Log.d("TeacherId", courseStudent?.courseId)
                                var courseRef = database.getReference("Courses")
                                courseRef.addValueEventListener(object: ValueEventListener{
                                    override fun onCancelled(p1: DatabaseError?) {

                                    }

                                    override fun onDataChange(p1: DataSnapshot?) {
                                        for (it2 in p1?.children!!){
                                            val course = it2.getValue(Course::class.java)
                                        Log.d("FoundedCourse1", course?.id)
                                        Log.d("FoundedCourse2", courseStudent?.courseId)
                                        Log.d("FoundendCourse3",courseStudent?.courseId?.equals(course?.id).toString())
                                            if (courseStudent?.courseId?.equals(course?.id)!!){
                                                courses.add(course!!)
                                            }
                                        }
                                        Log.d("ListCourses",courses.toString())
                                        teacherMenuView.setCoursesFromDb(courses)
                                        teacherMenuView.showCoursesList()
                                    }

                                })
                            }
                        }

                    }

                })

            }
            TEACHER->{
                teacherMenuView.setTypeUserMenu(typeUser)
                var myRef = database.getReference("Courses")
//                var courses = ArrayList<Course>()
                myRef.addValueEventListener(object:ValueEventListener{
                    override fun onCancelled(p0: DatabaseError?) {

                    }

                    override fun onDataChange(p0: DataSnapshot?) {
                        var courses = ArrayList<Course>()
                        for (it in p0?.children!!){
                            Log.d("CourseLoad", it.toString())
                            val courseTeacher = it.getValue(Course::class.java)
                            if (courseTeacher?.teacherId.equals(mAuth.currentUser?.uid)){
                                courses.add(courseTeacher!!)
//                                Log.d("TeacherId", courseTeacher?.courseId)
//                                var courseRef = database.getReference("Courses")
//                                courseRef.addValueEventListener(object: ValueEventListener{
//                                    override fun onCancelled(p1: DatabaseError?) {
//
//                                    }
//
//                                    override fun onDataChange(p1: DataSnapshot?) {
////                                        var courses = ArrayList<Course>()
//                                        for (it2 in p1?.children!!){
//                                            val course = it2.getValue(Course::class.java)
//                                        Log.d("FoundedCourse1", course?.id)
//                                        Log.d("FoundedCourse2", courseTeacher?.courseId)
////                                        Log.d("FoundendCourse3",courseTeacher?.courseId?.equals(course?.id).toString())
//                                            if (courseTeacher?.courseId?.equals(course?.id)!!){
//                                                Log.d("FoundendCourse3",courseTeacher?.courseId?.equals(course?.id).toString())
//                                                courses.add(course!!)
////                                                teacherMenuView.addCourseFromDb(course!!)
////                                                teacherMenuView.showCoursesList()
//                                            }
//                                        }
//                                        Log.d("Finished Course",courses.toString())
//                                        teacherMenuView.setCoursesFromDb(courses)
//                                        teacherMenuView.showCoursesList()
//                                    }
//
//                                })
                            }
                        }
//
                        teacherMenuView.setCoursesFromDb(courses)
                        teacherMenuView.showCoursesList()
                    }

                })
            }
        }

    }

}
val STUDENT:String = "STUDENT"
val TEACHER:String = "TEACHER"