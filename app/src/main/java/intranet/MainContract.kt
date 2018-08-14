package intranet

interface MainContract{
    interface View{
        fun startloading()
        fun stopLoading()
    }
    interface Presenter{
        fun onStart(v : View)
        fun onFinish()
    }

}