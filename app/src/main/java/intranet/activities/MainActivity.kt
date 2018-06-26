package intranet.activities

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.user.intranet2.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        addStudent(1,"Askhat",0.0)
        buttonSignUp.setOnClickListener {
            val intent = Intent(this, intranet.activities.UserActivity::class.java)
            startActivity(intent)
        }
    }
}