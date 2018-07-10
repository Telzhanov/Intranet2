package intranet.models

import java.io.Serializable

class Student(name:String,id:Int,var gpa : Double):Person(name,id),Serializable {

}