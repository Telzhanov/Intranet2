package intranet.views

import intranet.models.Course

interface ChooseListAdapterActivity{
    fun close()
    fun createCrouse(course: Course)
}