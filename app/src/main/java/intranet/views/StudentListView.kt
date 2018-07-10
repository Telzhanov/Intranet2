package intranet.views

import intranet.MainContract
import intranet.models.Student

interface StudentListView: MainContract.View{
    fun addStudent(students : ArrayList<Student>)
}