package intranet.models

import android.arch.persistence.room.*
import io.reactivex.Flowable

@Entity(tableName = "UserData")
data class User(
        @PrimaryKey(autoGenerate = true)
        var id: Int,
        @ColumnInfo(name = "name") var name: String
)
@Dao
interface UserDao {

    @Query("SELECT * FROM UserData")
    fun getAllPeople(): Flowable<List<User>>

    @Insert
    fun insert(user: User)
}
@Database(entities = arrayOf(User::class,StudentDb::class,TeacherDb::class), version = 2)
abstract class MyDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun studentDao():StudentDao
    abstract fun teacherDao():TeacherDao
}
@Entity(tableName = "StudentData")
data class StudentDb(
        @PrimaryKey
        var id:Int,
        @ColumnInfo(name="name") var name:String,
        @ColumnInfo(name="gpa") var gpa:Double
)
@Dao
interface StudentDao{
    @Query("SELECT * FROM StudentData")
    fun getAllStudents(): Flowable<List<StudentDb>>
    @Insert
    fun insert(studentDb:StudentDb)
}
@Entity(tableName = "TeacherData")
data class TeacherDb(
        @PrimaryKey
        var id:Int,
        @ColumnInfo(name="name") var name:String,
        @ColumnInfo(name="faculty") var faculty:String
)
@Dao
interface TeacherDao{
    @Query("SELECT * FROM TeacherData")
    fun getAllTeacher(): Flowable<List<TeacherDb>>
    @Insert
    fun insert(teacherDb:TeacherDb)
}

