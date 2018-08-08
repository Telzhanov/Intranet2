package intranet.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.user.intranet2.R
import intranet.models.Student
import intranet.views.CourseStudentListAdapterActivity
import kotlinx.android.synthetic.main.card.view.*

class StudentListAdapter(var students:ArrayList<Student>,var context: Context): RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    var parent:ViewGroup?=null
    var courseStudentListAdapterActivity = context as CourseStudentListAdapterActivity
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        this.parent=parent
        return StudentsListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.put_mark_card,parent,false))
    }

    override fun getItemCount(): Int {
        return students.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder.itemView.descView.text = students[position].name
        holder.itemView.textPost.text = students[position].email
        holder.itemView.authorPost.setOnClickListener {
            courseStudentListAdapterActivity.showDialog(students[position].id)
        }
    }

}

class StudentsListViewHolder(v: View):RecyclerView.ViewHolder(v){
}