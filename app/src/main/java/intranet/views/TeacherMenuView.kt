package intranet.views

import intranet.MainContract
import intranet.models.Course
import intranet.models.Person
import intranet.models.Teacher

interface TeacherMenuView:MainContract.View{
    fun setCoursesFromDb(courses:ArrayList<Course>)
    fun showCoursesList()
//    fun addCourseFromDb(course:Course)
    fun setTypeUserMenu(typeUser:String)
    fun setTeachersFromDb(teachers:ArrayList<Teacher>)
    fun setUserFromDb(user: Person)
    fun setGpaFromDb(gpa:Double)
}