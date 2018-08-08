package intranet.presenters

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import intranet.MainContract
import intranet.models.Course
import intranet.models.Student
import intranet.models.StudentCourses
import intranet.models.StudentsMarks
import intranet.views.CourseStudentListView
import java.text.SimpleDateFormat
import java.util.*




class CourseStudentListPresenter:MainContract.Presenter{
    lateinit var courseStudentListView:CourseStudentListView
    private var mAuth = FirebaseAuth.getInstance()
    var database = FirebaseDatabase.getInstance()
    var courseId:String?=null
    override fun onStart(v: MainContract.View) {
        courseStudentListView = v as CourseStudentListView
    }

    override fun onFinish() {

    }
    fun loadStudents(course:Course){
        var studentListId = database.getReference("StudentCourses").addValueEventListener(object: ValueEventListener {
            override fun onCancelled(p2: DatabaseError?) {

            }

            override fun onDataChange(p2: DataSnapshot?) {
                var students = ArrayList<Student>()
                for (it2 in p2?.children!!){
                    var studentCourse = it2.getValue(StudentCourses::class.java)
                    Log.d("Proverka2",course?.id?.equals(studentCourse?.courseId).toString())
                    if (course?.id?.equals(studentCourse?.courseId)!!){
                        courseId = course?.id
                        var studentsList = database.getReference("Students").addValueEventListener(object: ValueEventListener {
                            override fun onCancelled(p3: DatabaseError?) {

                            }
                            override fun onDataChange(p3: DataSnapshot?) {
                                for (it3 in p3?.children!!){
                                    Log.d("Proverka3",studentCourse?.studentId!!.equals(it3.key).toString())
                                    if (studentCourse?.studentId!!.equals(it3.key)){
                                        var student = it3.getValue(Student::class.java)
                                        students.add(student!!)

                                    }
                                }
                                Log.d("Proverka4",students.toString())
                                courseStudentListView.setStudentsFromDb(students)
                                courseStudentListView.showStudentList()
                            }

                        })
                    }
                }
            }

        })
    }
    fun createMarkForStudent(mark:Int,studentId:String){
        val c = Calendar.getInstance()
        val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        val strDate = sdf.format(c.time)
        var itemMark = database.getReference("StudentsMark").push()
        var studentMark = StudentsMarks(studentId,courseId!!,mark,strDate)
        itemMark.setValue(studentMark).addOnCompleteListener{
            if (it.isSuccessful){

            }
            else{

            }
        }
    }

}