package intranet.views

import intranet.MainContract
import intranet.models.Student

interface CourseStudentListView: MainContract.View{
    fun setStudentsFromDb(students:ArrayList<Student>)
    fun showStudentList()
}