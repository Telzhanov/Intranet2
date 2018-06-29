package intranet.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.user.intranet2.R
import intranet.fragments.HEADER
import intranet.models.Student
import kotlinx.android.synthetic.main.card.view.*

class ShowAllUsersAdapter(var dataset: ArrayList<Any>):RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        when(dataset[viewType]){
            HEADER->{
                return HeaderViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.show_all_users_header,parent,false))
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

            }
            is StudentsViewHolder->{
                var s = dataset[position] as Student
                holder.itemView.nameUser.text = s.name
                holder.itemView.idUser.text = s.id.toString()
                holder.itemView.gpaUser.text = s.gpa.toString()
            }
        }
    }
}
class HeaderViewHolder(v: View):RecyclerView.ViewHolder(v)
class StudentsViewHolder(v: View):RecyclerView.ViewHolder(v)
class TeachersViewHolder(v: View):RecyclerView.ViewHolder(v)
class FooterViewHolder(v:View):RecyclerView.ViewHolder(v)