package intranet.views

import intranet.MainContract
import intranet.models.Course

interface ChooseCourseView: MainContract.View{
    fun setCourseFromDb(courses:ArrayList<Course>)
    fun showCourseList()
}