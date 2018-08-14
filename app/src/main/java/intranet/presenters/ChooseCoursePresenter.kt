package intranet.presenters

import intranet.MainContract
import intranet.models.Course
import intranet.models.CoursesRepo
import intranet.models.Teacher
import intranet.views.ChooseCourseView
import org.koin.core.parameter.parametersOf
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject

class ChooseCoursePresenter:MainContract.Presenter,ChooseCourseBasePresenter,KoinComponent{
    val coursesRepo:CoursesRepo by inject { parametersOf(this)}
    lateinit var chooseCourseView: ChooseCourseView
    var courses=ArrayList<Course>()
    var teachers=ArrayList<Teacher>()
    override fun addCourse(course: Course) {
        courses.add(course)
    }
    override fun addTeacher(teacher: Teacher) {
        teachers.add(teacher)
        chooseCourseView.showCourseList(courses,teachers)
    }

    override fun onStart(v: MainContract.View) {
        chooseCourseView = v as ChooseCourseView
    }
    override fun onFinish() {

    }
    fun loadCourses(){
        coursesRepo.getCoursesFromDb()
    }
    fun createCourse(course:Course){
        coursesRepo.createCourse(course)
    }

}