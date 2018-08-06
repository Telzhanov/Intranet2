package intranet.views

import intranet.MainContract
import intranet.models.Course

interface TeacherMenuView:MainContract.View{
    fun setCoursesFromDb(courses:ArrayList<Course>)
    fun showCoursesList()
//    fun addCourseFromDb(course:Course)
    fun setTypeUserMenu(typeUser:String)
}