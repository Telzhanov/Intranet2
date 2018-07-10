package intranet.views

import intranet.MainContract
import intranet.models.Student
import intranet.models.Teacher

interface UserListView: MainContract.View{
    fun addStudent(students : ArrayList<Student>)
    fun addTeacher(teachers : ArrayList<Teacher>)
}