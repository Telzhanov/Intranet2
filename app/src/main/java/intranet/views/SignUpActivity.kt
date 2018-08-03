package intranet.views

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.user.intranet2.R
import intranet.presenters.SignUpPresenter
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUpActivity : SignUpView, AppCompatActivity() {
    var typeUser: String = STUDENT
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
            signUpPresenter.signUpUser(registerEmail.text.toString(),registerPassword.text.toString(),typeUser,registerName.text.toString())
            finish()
        }
        radioGroup.setOnCheckedChangeListener{
            group, checkedId ->
            when(checkedId){
                R.id.radioStudent->{
                    typeUser = STUDENT
                }
                R.id.radioTeacher->{
                    typeUser = TEACHER
                }
            }
        }
    }

}
val STUDENT: String = "STUDENT"
val TEACHER: String = "TEACHER"