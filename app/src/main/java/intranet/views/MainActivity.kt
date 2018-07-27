package intranet.views

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.user.intranet2.R
import intranet.presenters.MainPresenter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity:LoginView, AppCompatActivity() {
    val mainPresenter: MainPresenter
    override fun startloading() {

    }

    override fun stopLoading() {

    }

    init{
        mainPresenter = MainPresenter()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainPresenter.onStart(this)
        buttonSignUp.setOnClickListener {
            if (mainPresenter.checkUser(editId.text.toString().toInt(),editPassword.text.toString())){
                val intent = Intent(this, intranet.views.UserActivity::class.java)
                startActivity(intent)
            }

        }
    }
}