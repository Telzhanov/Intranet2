package intranet.presenters

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import intranet.MainContract
import intranet.models.Course
import intranet.models.StudentsMarks
import intranet.views.StudentMarkView

class StudentMarkPresenter:MainContract.Presenter {
    lateinit var studentMarkView: StudentMarkView
    private var mAuth = FirebaseAuth.getInstance()
    var database = FirebaseDatabase.getInstance()
    override fun onStart(v: MainContract.View) {
        studentMarkView = v as StudentMarkView
    }

    override fun onFinish() {

    }

    fun loadMarks(course:Course) {
        var marks = database.getReference("StudentsMark")
        marks.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError?) {

            }

            override fun onDataChange(p0: DataSnapshot?) {
                var marks = ArrayList<StudentsMarks>()
                for (it in p0?.children!!){
                    var studentMark = it.getValue(StudentsMarks::class.java)
                    if (studentMark?.courseId==course.id && mAuth.currentUser?.uid==studentMark?.studentId){
                        marks.add(studentMark!!)
                    }
                }
                studentMarkView.setStudentsMarkFromDb(marks)
                studentMarkView.showListMarks()
            }

        })

    }
}
