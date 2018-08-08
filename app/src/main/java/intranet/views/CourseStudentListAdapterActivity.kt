package intranet.views

interface CourseStudentListAdapterActivity{
    fun putMark(mark:Int,studentId:String)
    fun showDialog(studentId: String)
}