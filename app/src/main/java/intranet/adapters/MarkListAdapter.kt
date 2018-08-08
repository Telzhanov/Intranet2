package intranet.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.user.intranet2.R
import intranet.models.StudentsMarks
import kotlinx.android.synthetic.main.mark_card.view.*

class MarkListAdapter(var marks:ArrayList<StudentsMarks>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MarkViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.mark_card,parent,false))
    }

    override fun getItemCount(): Int {
        return marks.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder.itemView.pointText.text = "Points: " + marks[position].mark.toString()
        holder.itemView.dateText.text = marks[position].date
    }
}
 class MarkViewHolder(v:View):RecyclerView.ViewHolder(v)