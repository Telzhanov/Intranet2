package intranet.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.user.intranet2.R
import intranet.models.Course
import kotlinx.android.synthetic.main.card.view.*

class DisciplineListTeacherAdapter(var courses:ArrayList<Course>):RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return DisciplineViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.card,parent,false))
    }

    override fun getItemCount(): Int {
        return courses.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder.itemView.descView.text = courses[position].name
        holder.itemView.authorPost.text = "ID of course: "+ courses[position].id
        holder.itemView.textPost.text = "Credits: "+ courses[position].credits.toString()
    }

}

class DisciplineViewHolder(v: View):RecyclerView.ViewHolder(v)
