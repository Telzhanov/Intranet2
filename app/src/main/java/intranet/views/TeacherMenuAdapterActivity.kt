package intranet.views

import intranet.models.Course

interface TeacherMenuAdapterActivity{
    fun showListStudents(course:Course)
    fun showMarksStudents(course: Course)
}