package intranet.views

import intranet.MainContract
import intranet.models.Course
import intranet.models.Teacher

interface ChooseCourseView: MainContract.View{
    fun showCourseList(courses: ArrayList<Course>,teachers:ArrayList<Teacher>)
    fun callToast()
}