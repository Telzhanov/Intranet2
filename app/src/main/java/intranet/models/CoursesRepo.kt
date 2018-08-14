package intranet.models

interface CoursesRepo{
    fun getCoursesFromDb()
    fun createCourse(course:Course)
}