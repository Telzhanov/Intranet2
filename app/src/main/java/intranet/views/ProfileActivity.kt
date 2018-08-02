package intranet.views

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.user.intranet2.R

open class ProfileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
//        var student =getIntent().getSerializableExtra("object") as Student
//        profileName.text = student.name
//        idProfile.text = student.id.toString()
//        gpaProfile.text = student.gpa.toString()
//        closeProfile.setOnClickListener{
//            finish()
//        }
    }
}
