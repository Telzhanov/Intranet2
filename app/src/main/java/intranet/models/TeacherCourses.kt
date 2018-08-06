package intranet.models

class TeacherCourses{
    var teacherId: String? =null
    var courseId:String? =null
    constructor()
    constructor(teacherId:String, courseId:String){
        this.teacherId=teacherId
        this.courseId=courseId
    }
}