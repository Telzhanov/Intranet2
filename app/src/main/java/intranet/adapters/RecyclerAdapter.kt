package intranet.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.user.intranet2.R
import intranet.activities.MainActivity.Companion.students
import kotlinx.android.synthetic.main.card.view.*

class RecyclerAdapter: RecyclerView.Adapter<ViewHolder>(){
    override fun getItemCount(): Int {
        return students.size
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val myViews = layoutInflater.inflate(R.layout.card,parent,false)
        return ViewHolder(myViews)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.nameUser.text = students[position].name
        holder.itemView.idUser.text = students[position].id.toString()
        holder.itemView.gpaUser.text = students[position].gpa.toString()
    }

}
class ViewHolder(v: View):RecyclerView.ViewHolder(v){

}