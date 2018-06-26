package intranet.adapters

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import intranet.fragments.StudentsFragment
import intranet.fragments.TeacherFragment

class MyPagerAdapter(fr : FragmentManager): FragmentPagerAdapter(fr) {
    override fun getCount(): Int {
        return 2
    }
    override fun getItem(position: Int): Fragment? {
        when(position){
            0->{
                val studentsFragment= StudentsFragment.newInstance("", "")
                return studentsFragment
            }
            1->{
                val teacherFragment= TeacherFragment.newInstance("", "")
                return teacherFragment
            }
            else ->return null
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        when(position){
            0->return "Students"
            1->return "Teachers"
            else->return null
        }
    }
}