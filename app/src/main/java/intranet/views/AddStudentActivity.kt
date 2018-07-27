package intranet.views

import android.os.Bundle
import android.support.v7.app.ActionBar
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import com.example.user.intranet2.R
import intranet.presenters.AddStudentPresenter
import kotlinx.android.synthetic.main.activity_add_student.*

class AddStudentActivity() : AppCompatActivity() {
//    lateinit var refreshAcitivity:RefreshAcitivity
    var addStudentPresenter:AddStudentPresenter
    init{
        addStudentPresenter=AddStudentPresenter()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_student)
//        val userActivity=UserActivity()
//        refreshAcitivity = userActivity
        setSupportActionBar(toolbarOfAddStudent)
        var actionBar: ActionBar? = supportActionBar
        actionBar?.title="Add Student"
        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar?.setHomeAsUpIndicator(R.drawable.ic_navigate_before_black_24dp)
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        var menuInflater: MenuInflater = menuInflater
        menuInflater.inflate(R.menu.add_student_menu,menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            R.id.idDone->{
                addStudentPresenter.addStudent(registerId.text.toString().toInt(),registerName.text.toString(),registerGpa.text.toString().toDouble(),"")
//                refreshAcitivity.refreshData()
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
