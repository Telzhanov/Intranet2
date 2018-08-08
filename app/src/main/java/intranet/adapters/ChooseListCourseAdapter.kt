package intranet.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.user.intranet2.R
import intranet.models.Course
import intranet.models.Teacher
import intranet.views.ChooseListAdapterActivity
import kotlinx.android.synthetic.main.card.view.*

class ChooseListCourseAdapter(var courses:ArrayList<Course>, var context:Context,var teachers:ArrayList<Teacher>): RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    var chooseListAdapterActivity = context as ChooseListAdapterActivity
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return CourseViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.card,parent,false))
    }

    override fun getItemCount(): Int {
        return courses.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder.itemView.descView.text = courses[position].name
        holder.itemView.authorPost.text = "Teacher:" + teachers[position].name
        holder.itemView.textPost.text = "Credits: " + courses[position].credits.toString()
        holder.itemView.setOnClickListener {
            chooseListAdapterActivity.createCrouse(courses[position])
            chooseListAdapterActivity.close()
        }
    }

}

class CourseViewHolder(v: View):RecyclerView.ViewHolder(v)