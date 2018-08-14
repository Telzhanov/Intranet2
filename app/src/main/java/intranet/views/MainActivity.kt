package intranet.views

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.user.intranet2.R
import intranet.presenters.MainPresenter
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject

class MainActivity:LoginView, AppCompatActivity() {
    override fun openList() {
        val intent = Intent(this, TeacherMenuActivity::class.java)
        startActivity(intent)
        finish()
    }
    val mainPresenter:MainPresenter by inject()
    override fun startloading() {

    }

    override fun stopLoading() {

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainPresenter.onStart(this)
        buttonSignIn.setOnClickListener{
            mainPresenter.checkUser(editId.text.toString(),editPassword.text.toString())
        }
        buttonSignUp.setOnClickListener{
            var intent = Intent(this,SignUpActivity::class.java)
            startActivity(intent)
        }
    }

}