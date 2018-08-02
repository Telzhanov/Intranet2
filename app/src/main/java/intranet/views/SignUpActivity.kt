package intranet.views

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.user.intranet2.R
import intranet.presenters.SignUpPresenter
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUpActivity : SignUpView, AppCompatActivity() {
    override fun startloading() {

    }

    override fun stopLoading() {

    }

    var signUpPresenter: SignUpPresenter
    init {
        signUpPresenter = SignUpPresenter()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        signUpPresenter.onStart(this)
        signUpButton.setOnClickListener{
            signUpPresenter.signUpUser(registerEmail.text.toString(),registerPassword.text.toString())
            finish()
        }
    }
}
