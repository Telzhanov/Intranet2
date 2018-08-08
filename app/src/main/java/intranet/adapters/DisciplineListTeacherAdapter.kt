package intranet.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.user.intranet2.R
import intranet.models.Course
import intranet.models.Teacher
import intranet.presenters.STUDENT
import intranet.presenters.TEACHER
import intranet.views.TeacherMenuAdapterActivity
import kotlinx.android.synthetic.main.card.view.*

class DisciplineListTeacherAdapter(var courses:ArrayList<Course>,var context: Context,var typeUser:String,var teachers: ArrayList<Teacher>):RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    var teacherMenuAdapterActivity = context as TeacherMenuAdapterActivity
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return DisciplineViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.card,parent,false))
    }

    override fun getItemCount(): Int {
        return courses.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(typeUser){
            TEACHER->{
                holder.itemView.descView.text = courses[position].name
                holder.itemView.authorPost.text =""
                holder.itemView.textPost.text = "Credits: "+ courses[position].credits.toString()
                holder.itemView.setOnClickListener {
                    teacherMenuAdapterActivity.showListStudents(courses[position])
                }

            }
            STUDENT->{
                holder.itemView.descView.text = courses[position].name
                holder.itemView.authorPost.text = "Teacher: " + teachers[position].name
                holder.itemView.textPost.text = "Credits: "+ courses[position].credits.toString()
                holder.itemView.setOnClickListener {
                    teacherMenuAdapterActivity.showMarksStudents(courses[position])
                }
            }
        }
    }

}

class DisciplineViewHolder(v: View):RecyclerView.ViewHolder(v)
