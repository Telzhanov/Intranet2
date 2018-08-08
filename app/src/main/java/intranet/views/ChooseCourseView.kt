package intranet.views

import intranet.MainContract
import intranet.models.Course
import intranet.models.Teacher

interface ChooseCourseView: MainContract.View{
    fun setCourseFromDb(courses:ArrayList<Course>)
    fun setTeacherFromDb(teachers:ArrayList<Teacher>)
    fun showCourseList()
    fun callToast()
}