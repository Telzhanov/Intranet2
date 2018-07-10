package intranet.adapters

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import intranet.views.AllUsersFragment
import intranet.views.StudentsFragment
import intranet.views.TeacherFragment

class UsersPagesAdapter(fr : FragmentManager): FragmentPagerAdapter(fr) {
    override fun getCount(): Int {
        return 3
    }
    override fun getItem(position: Int): Fragment? {
        when(position){
            0->{
                val allUsersFragment= AllUsersFragment()
                return allUsersFragment
            }
            1->{
                val teacherFragment= TeacherFragment()
                return teacherFragment
            }
            2->{
                val studentFragment= StudentsFragment()
                return studentFragment
            }
            else ->return null
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        when(position){
            1->return "Teachers"
            0->return "All users"
            2->return "Students"
            else->return null
        }
    }
}