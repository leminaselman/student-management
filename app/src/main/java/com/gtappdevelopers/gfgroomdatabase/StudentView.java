package com.gtappdevelopers.gfgroomdatabase;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.gtappdevelopers.gfgroomdatabase.StudentModal;
import com.gtappdevelopers.gfgroomdatabase.StudentRepository;

import java.util.List;

public class StudentView extends AndroidViewModel {
    private StudentRepository repository;
    private LiveData<List<StudentModal>> allStudents;

    public StudentView(@NonNull Application application) {
        super(application);
        repository = new StudentRepository(application);
        allStudents = repository.getAllStudents();
    }

    public void insert(StudentModal model) {
        repository.insert(model);
    }

    public void update(StudentModal model) {
        repository.update(model);
    }

    public void delete(StudentModal model) {
        repository.delete(model);
    }

    public void deleteAllStudents() {
        repository.deleteAllStudents();
    }

    public LiveData<List<StudentModal>> getAllStudents() {
        return allStudents;
    }
}
