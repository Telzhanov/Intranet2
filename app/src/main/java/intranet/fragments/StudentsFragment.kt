package intranet.fragments

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.user.intranet2.R
import intranet.activities.MainActivity.Companion.registerAllStudentListener
import intranet.adapters.RecyclerAdapter
import kotlinx.android.synthetic.main.fragment_students.*

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class StudentsFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_students, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        registerAllStudentListener()
        studentRecyclerView.layoutManager= LinearLayoutManager(activity)
        studentRecyclerView.adapter= RecyclerAdapter()
    }

    override fun onResume() {
        super.onResume()
        studentRecyclerView.layoutManager= LinearLayoutManager(activity)
        studentRecyclerView.adapter= RecyclerAdapter()

    }
    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onDetach() {
        super.onDetach()
    }


    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                StudentsFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }
}
