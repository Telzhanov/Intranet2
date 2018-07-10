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
    }
}