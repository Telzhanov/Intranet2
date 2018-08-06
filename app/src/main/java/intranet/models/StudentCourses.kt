package intranet.models

class StudentCourses{
    var studentId:String?=null
    var courseId:String?=null
    constructor(studentId:String,courseId:String){
        this.studentId = studentId
        this.courseId = courseId
    }
    constructor()
}