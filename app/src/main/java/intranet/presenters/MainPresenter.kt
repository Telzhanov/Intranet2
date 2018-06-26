package intranet.presenters

import android.content.Context
import intranet.models.MyDatabase
import intranet.models.Student

class MainPresenter(context: Context):BasePresenter(context) {

}
object Data{
    var students = ArrayList<Student>()
    var database: MyDatabase? = null
}