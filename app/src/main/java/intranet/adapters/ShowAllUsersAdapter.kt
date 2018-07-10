package intranet.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.user.intranet2.R
import intranet.models.Student
import intranet.models.Teacher
import kotlinx.android.synthetic.main.card.view.*
import kotlinx.android.synthetic.main.show_all_users_header.view.*
class ShowAllUsersAdapter(var dataset: ArrayList<Any>, var context : Context):RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    var openProfileActivity:OpenProfileActivity
    init{
        openProfileActivity= context as OpenProfileActivity
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        when(dataset[viewType]){
            is String->{
                return HeaderViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.show_all_users_header,parent,false))
            }
            is Teacher->{
                return TeachersViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.card,parent,false))
            }
            else -> return StudentsViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.card,parent,false))
        }
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun getItemCount(): Int {
        return dataset.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is HeaderViewHolder->{
                    holder.itemView.toolbarRecycler.title=dataset[position].toString()
                }
            is StudentsViewHolder->{
                var s = dataset[position] as Student
                holder.itemView.nameUser.text = s.name
                holder.itemView.idUser.text = s.id.toString()
                holder.itemView.gpaUser.text = s.gpa.toString()
                holder.itemView.setOnClickListener {
                    openProfileActivity.openProfile(s)
                }
            }
            is TeachersViewHolder->{
                var t = dataset[position] as Teacher
                holder.itemView.nameUser.text = t.name
                holder.itemView.idUser.text=t.id.toString()
                holder.itemView.gpaTextView.text = "Faculty"
                holder.itemView.gpaUser.text = t.faculty
            }
        }
    }
}
class HeaderViewHolder(v: View):RecyclerView.ViewHolder(v)
class StudentsViewHolder(v: View):RecyclerView.ViewHolder(v)
class TeachersViewHolder(v: View):RecyclerView.ViewHolder(v)
interface OpenProfileActivity{
    fun openProfile(obj: Student)
}