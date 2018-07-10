package intranet.views

import intranet.MainContract
import intranet.models.Teacher

interface TeacherListView : MainContract.View{
    fun addTeacher(teachers : ArrayList<Teacher>)
}