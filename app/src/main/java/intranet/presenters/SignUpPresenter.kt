package intranet.presenters

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import intranet.MainContract



class SignUpPresenter: MainContract.Presenter{
    private var mAuth: FirebaseAuth? = null
    override fun onStart(v: MainContract.View) {
        mAuth = FirebaseAuth.getInstance()
    }

    override fun onFinish() {

    }
    fun signUpUser(email:String, password:String){
        mAuth?.createUserWithEmailAndPassword(email,password)
                ?.addOnCompleteListener{
                    if (it.isSuccessful){
                        var user: FirebaseUser? = mAuth?.currentUser
                    }
                    else{

                    }
                }
    }

}