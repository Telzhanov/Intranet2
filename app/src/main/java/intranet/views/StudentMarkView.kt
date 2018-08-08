package intranet.views

import intranet.MainContract
import intranet.models.StudentsMarks

interface StudentMarkView:MainContract.View{
    fun setStudentsMarkFromDb(marks:ArrayList<StudentsMarks>)
    fun showListMarks()
}