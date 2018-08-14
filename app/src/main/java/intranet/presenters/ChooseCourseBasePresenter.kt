package intranet.presenters

import intranet.MainContract
import intranet.models.Course
import intranet.models.Teacher

interface ChooseCourseBasePresenter:MainContract.Presenter{
    fun addCourse(course: Course)
    fun addTeacher(teacher: Teacher)
}