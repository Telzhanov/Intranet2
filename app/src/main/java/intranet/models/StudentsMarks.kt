package intranet.models

class StudentsMarks{
    var mark:Int= 0
    var studentId:String?=null
    var courseId:String?=null
    var date:String?=null
    constructor(studentId:String,courseId:String,mark:Int,date:String ){
        this.studentId = studentId
        this.courseId = courseId
        this.mark = mark
        this.date = date
    }
    constructor()
}