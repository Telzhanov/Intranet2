package intranet

import intranet.models.*
import intranet.presenters.ChooseCourseBasePresenter
import intranet.presenters.ChooseCoursePresenter
import intranet.presenters.MainPresenter
import org.koin.dsl.module.module

val appModule = module{
    factory {
        ChooseCoursePresenter() as ChooseCourseBasePresenter
    }
    factory {
        (chooseCourseListener:ChooseCourseBasePresenter) ->CourseRepoImpl(chooseCourseListener) as CoursesRepo
    }
    factory {
        StudentRepoImpl() as StudentRepo
    }
    factory {
        StudentCoursesRepoImpl() as StudentCoursesRepo
    }
    factory {
        StudentMarksRepoImpl() as StudentMarksRepo

    }
    factory {
        TeacherCoursesRepoImpl() as TeacherCoursesRepo
    }
    factory {
        TeacherRepoImpl() as TeacherRepo
    }
    factory{
        ChooseCoursePresenter()
    }
    factory{
        MainPresenter()
    }

}
const val CHOOSECOURSE = "CHOOSECOURSE"