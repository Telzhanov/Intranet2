package intranet.presenters

import com.google.firebase.auth.FirebaseAuth
import intranet.MainContract

class UserPresenter(): MainContract.Presenter{
    private var mAuth = FirebaseAuth.getInstance()
    override fun onStart(v: MainContract.View) {

    }

    override fun onFinish() {

    }
    fun signOut(){
        mAuth.signOut()
    }

}



