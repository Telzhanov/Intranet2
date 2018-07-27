package intranet

import android.app.Application
import android.arch.persistence.room.Room
import intranet.models.MyDatabase

class App:Application(){
    companion object{
        var database: MyDatabase? = null
    }
    override fun onCreate() {
        super.onCreate()
        database = Room.databaseBuilder(this, MyDatabase::class.java, "MyDB").build()
//        val student = StudentDb(1, "Askhat", 2.0,"12345")
//        Single.fromCallable {
//            App.database?.studentDao()?.insert(student)
//        }.subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread()).subscribe()
//        val teacher = TeacherDb(2, "Alexey", "FIT","12345")
//        Single.fromCallable {
//            App.database?.teacherDao()?.insert(teacher)
//        }.subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread()).subscribe()
    }

}