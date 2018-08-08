package intranet.models

import java.io.Serializable

class Course: Serializable{
    var id:String?=null
    var name:String?= null
    var credits:Int?=null
    var teacherId:String?=null
    constructor(name:String,id:String,credits:Int,teacherId:String){
        this.id=id
        this.name=name
        this.credits=credits
        this.teacherId=teacherId
    }
    constructor(name:String, credits:Int){
        this.name= name
        this.credits = credits
    }
    constructor()
}