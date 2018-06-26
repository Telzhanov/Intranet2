package intranet.activities

import android.arch.persistence.room.Room
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.example.user.intranet2.R
import intranet.models.MyDatabase
import intranet.models.Student
import intranet.models.StudentDb
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity: AppCompatActivity() {
    companion object {
        var students =ArrayList<Student>()
        var database: MyDatabase?=null
        fun addStudent(id: Int, name: String,gpa:Double) {
            val student = StudentDb(id,name,gpa)
            Single.fromCallable {
                database?.studentDao()?.insert(student)
            }.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread()).subscribe()
        }
        fun registerAllStudentListener() {
            database?.studentDao()?.getAllStudents()
                    ?.subscribeOn(Schedulers.io())
                    ?.observeOn(AndroidSchedulers.mainThread())
                    ?.subscribe { listOfStudents ->
                        Log.d("ashat",listOfStudents.toString())
                        students = ArrayList<Student>()
                        for (student in listOfStudents){
                            var s = Student(student.name,student.id,student.gpa)
                            students.add(s)
                        }
                        Log.d("students",students.size.toString())
                    }
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        database = Room.databaseBuilder(this, MyDatabase::class.java, "MyDB").build()
//        addStudent(1,"Askhat",0.0)
        buttonSignUp.setOnClickListener {
            val intent = Intent(this, intranet.activities.UserActivity::class.java)
            startActivity(intent)
        }
    }
}