package intranet.adapters

import android.service.autofill.Dataset
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.View.inflate
import android.view.ViewGroup
import com.example.user.intranet2.R
import intranet.fragments.HEADER
import intranet.fragments.STUDENTS

class ShowAllUsersAdapter(var dataset: ArrayList<Any>):RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        when(dataset[viewType]){
            HEADER->{
                return HeaderViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.show_all_users_header,parent,false))
            }
            else -> return StudentsViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.card,parent,false)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun getItemCount(): Int {
        return dataset.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(dataset[position])
    }
}
class HeaderViewHolder(v: View):RecyclerView.ViewHolder(v)
class StudentsViewHolder(v: View):RecyclerView.ViewHolder(v)
class TeachersViewHolder(v: View):RecyclerView.ViewHolder(v)
class FooterViewHolder(v:View):RecyclerView.ViewHolder(v)