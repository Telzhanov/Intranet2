package intranet.presenters

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import intranet.MainContract
import intranet.models.*
import intranet.views.TeacherMenuView


class TeacherMenuPresenter:MainContract.Presenter{
    lateinit var teacherMenuView: TeacherMenuView
    private var mAuth = FirebaseAuth.getInstance()
    var database = FirebaseDatabase.getInstance()
    lateinit var user:Person
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
                    Log.d("CheckTeacher",it.key.equals(mAuth.currentUser?.uid).toString())
                    if (it.key.equals(mAuth.currentUser?.uid)){
                        var teacher = it.getValue(Teacher::class.java)
                        user = teacher as Person
                        teacherMenuView.setUserFromDb(user)
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
                                var student = it.getValue(Student::class.java)
                                user = student as Person
                                teacherMenuView.setUserFromDb(user)
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
    fun calculateGpa(){
        var overallGpa = 0.0
        var overallCredits = 0
        var myRef = database.getReference("StudentCourses")

//                var courses = ArrayList<Course>()
        myRef.addValueEventListener(object:ValueEventListener{
            override fun onCancelled(p0: DatabaseError?) {

            }

            override fun onDataChange(p0: DataSnapshot?) {
                for (it in p0?.children!!){
                    val courseStudent = it.getValue(StudentCourses::class.java)
                    if (courseStudent?.studentId.equals(mAuth.currentUser?.uid)){
                        var courseRef = database.getReference("Courses")
                        courseRef.addValueEventListener(object: ValueEventListener{
                            override fun onCancelled(p1: DatabaseError?) {

                            }

                            override fun onDataChange(p1: DataSnapshot?) {
                                var courses = ArrayList<Course>()
                                for (it2 in p1?.children!!){
                                    val course = it2.getValue(Course::class.java)
                                    if (courseStudent?.courseId?.equals(course?.id)!!){
                                        courses.add(course!!)
                                    }
                                }
                                Log.d("ListCoursesMark",courses.toString())

                                var getMarksByCourse = database.getReference("StudentsMark").addValueEventListener(object: ValueEventListener{
                                    override fun onCancelled(p0: DatabaseError?) {
                                    }
                                    override fun onDataChange(p0: DataSnapshot?) {
                                        var marks = HashMap<String, Int>()
                                        var totalGpa = 0.0
                                        for (course in courses){
                                            var marksCourse = 0
                                            var gpa = 0.0
                                            overallCredits = overallCredits + course.credits!!

                                            for (it in p0?.children!!){
                                                var studentMark = it.getValue(StudentsMarks::class.java)
                                                if (course.id.equals(studentMark?.courseId) && mAuth.currentUser?.uid.equals(studentMark?.studentId)){
                                                    marksCourse=marksCourse+studentMark?.mark!!

                                                }
                                            }
                                            if (marksCourse<44 ){
                                                gpa = 0.0
                                            }
                                            if (marksCourse>=44 && marksCourse<54){
                                                gpa = 1.0
                                            }
                                            if (marksCourse>=54 && marksCourse<59){
                                                gpa = 2.0
                                            }
                                            if (marksCourse>=59 && marksCourse<64){
                                                gpa = 2.25
                                            }
                                            if (marksCourse>=64 && marksCourse<69){
                                                gpa = 2.5
                                            }
                                            if (marksCourse>=69 && marksCourse<74){
                                                gpa = 3.0
                                            }
                                            if (marksCourse>=74 && marksCourse<80){
                                                gpa = 3.25
                                            }
                                            if (marksCourse>=80 && marksCourse<85){
                                                gpa = 3.5
                                            }
                                            if (marksCourse>=85 && marksCourse<90){
                                                gpa = 3.75
                                            }
                                            if (marksCourse>=90){
                                                gpa = 4.0
                                            }
                                            overallGpa=(gpa*course.credits!!)+overallGpa
                                        }
                                        totalGpa = overallGpa/overallCredits
                                        teacherMenuView.setGpaFromDb(totalGpa)
                                    }

                                })
                            }
                        })
                    }
                }


            }

        })



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
                        var teachers = ArrayList<Teacher>()
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
                                                        teacherMenuView.setCoursesFromDb(courses)
                                                        teacherMenuView.setTeachersFromDb(teachers)
                                                        teacherMenuView.showCoursesList()
                                                    }

                                                })
                                            }
                                        }
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
                        var teachers = ArrayList<Teacher>()
                        for (it in p0?.children!!){
                            Log.d("CourseLoad", it.toString())
                            val courseTeacher = it.getValue(Course::class.java)
                            if (courseTeacher?.teacherId.equals(mAuth.currentUser?.uid)){
                                courses.add(courseTeacher!!)
                            }
                        }
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