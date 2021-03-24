package com.gtappdevelopers.gfgroomdatabase;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;
@Dao
public interface StudentDao {

    @Insert
    void insert(StudentModal model);

    @Update
    void update(StudentModal model);

    @Delete
    void delete(StudentModal model);

    @Query("DELETE FROM student_table")
    void deleteAllStudents();

    @Query("SELECT * FROM student_table ORDER BY identity ASC")
    LiveData<List<StudentModal>> getAllStudents();


}


