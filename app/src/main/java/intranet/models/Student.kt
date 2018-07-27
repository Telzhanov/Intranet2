package intranet.models

import java.io.Serializable

class Student(name:String,id:Int,password:String,var gpa : Double):Person(name,id,password),Serializable {

}