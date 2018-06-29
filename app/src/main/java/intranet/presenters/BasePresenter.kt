package intranet.presenters

import android.arch.persistence.room.Room
import android.content.Context
import intranet.models.MyDatabase
import intranet.models.StudentDb
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

open class BasePresenter(var context : Context){
    init{
        Data.database = Room.databaseBuilder(context, MyDatabase::class.java, "MyDB").build()
    }

}