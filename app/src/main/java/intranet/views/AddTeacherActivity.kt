package intranet.views

import android.os.Bundle
import android.support.v7.app.ActionBar
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import com.example.user.intranet2.R
import intranet.presenters.AddTeacherPresenter
import kotlinx.android.synthetic.main.activity_add_teacher.*

class AddTeacherActivity : AppCompatActivity() {
    val addTeacherPresenter:AddTeacherPresenter
    init {
        addTeacherPresenter= AddTeacherPresenter()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_teacher)
        setSupportActionBar(toolbarOfAddTeacher)
        var actionBar: ActionBar? = supportActionBar
        actionBar?.title="Add teacher"
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
                addTeacherPresenter.addTeacher(registerTeacherId.text.toString().toInt(),registerTeacherName.text.toString(),registerFaculty.text.toString())
//                refreshAcitivity.refreshData()
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
