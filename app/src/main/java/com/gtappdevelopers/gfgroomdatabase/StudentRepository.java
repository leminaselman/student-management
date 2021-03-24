package com.gtappdevelopers.gfgroomdatabase;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;
public class StudentRepository {

    private StudentDao dao;
    private LiveData<List<StudentModal>> allStudents;
    public StudentRepository(Application application) {
        StudentDatabase database = StudentDatabase.getInstance(application);
        dao = database.studentDao();
        allStudents = dao.getAllStudents();
    }


    public void insert(StudentModal model) {
        new InsertStudentAsyncTask(dao).execute(model);
    }

    public void update(StudentModal model) {
        new UpdateStudentAsyncTask(dao).execute(model);
    }

    public void delete(StudentModal model) {
        new DeleteStudentAsyncTask(dao).execute(model);
    }

    public void deleteAllStudents() {
        new DeleteAllStudentsAsyncTask(dao).execute();
    }

    public LiveData<List<StudentModal>> getAllStudents() {
        return allStudents;
    }

    private static class InsertStudentAsyncTask extends AsyncTask<StudentModal, Void, Void> {
        private StudentDao dao;
        private InsertStudentAsyncTask(StudentDao dao) {
            this.dao = dao;
        }
        @Override
        protected Void doInBackground(StudentModal... model) {
            dao.insert(model[0]);
            return null;
        }
    }

    private static class UpdateStudentAsyncTask extends AsyncTask<StudentModal, Void, Void> {
        private StudentDao dao;
        private UpdateStudentAsyncTask(StudentDao dao) {
            this.dao = dao;
        }
        @Override
        protected Void doInBackground(StudentModal... models) {
            dao.update(models[0]);
            return null;
        }
    }

    private static class DeleteStudentAsyncTask extends AsyncTask<StudentModal, Void, Void> {
        private StudentDao dao;
        private DeleteStudentAsyncTask(StudentDao dao) {
            this.dao = dao;
        }
        @Override
        protected Void doInBackground(StudentModal... models) {
            dao.delete(models[0]);
            return null;
        }
    }

    private static class DeleteAllStudentsAsyncTask extends AsyncTask<Void, Void, Void> {
        private StudentDao dao;
        private DeleteAllStudentsAsyncTask(StudentDao dao) {
            this.dao = dao;
        }
        @Override
        protected Void doInBackground(Void... voids) {
            dao.deleteAllStudents();
            return null;
        }
    }
}
