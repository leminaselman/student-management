package com.gtappdevelopers.gfgroomdatabase;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.gtappdevelopers.gfgroomdatabase.StudentView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView studentsRV;
    private static final int ADD_STUDENT_REQUEST = 1;
    private static final int EDIT_STUDENT_REQUEST = 2;
    private com.gtappdevelopers.gfgroomdatabase.StudentView viewmodalstudent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_student);
        studentsRV = findViewById(R.id.idRVStudents);
        FloatingActionButton fab = findViewById(R.id.idFABAdd);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NewStudentActivity.class);
                startActivityForResult(intent, ADD_STUDENT_REQUEST);
            }
        });

        studentsRV.setLayoutManager(new LinearLayoutManager(this));
        studentsRV.setHasFixedSize(true);
        final StudentRVAdapter adapter = new StudentRVAdapter();
        studentsRV.setAdapter(adapter);
        viewmodalstudent = ViewModelProviders.of(this).get(StudentView.class);
        viewmodalstudent.getAllStudents().observe(this, new Observer<List<StudentModal>>() {
            @Override
            public void onChanged(List<StudentModal> models) {
                adapter.submitList(models);
            }
        });
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                viewmodalstudent.delete(adapter.getStudentAt(viewHolder.getAdapterPosition()));
                Toast.makeText(MainActivity.this, "Student deleted", Toast.LENGTH_SHORT).show();
            }
        }).
                        attachToRecyclerView(studentsRV);
        adapter.setOnItemClickListener(new StudentRVAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(StudentModal model) {
                Intent intent = new Intent(MainActivity.this, NewStudentActivity.class);
                intent.putExtra(NewStudentActivity.EXTRA_ID, model.getId());
                intent.putExtra(NewStudentActivity.EXTRA_NAME, model.getName());
                intent.putExtra(NewStudentActivity.EXTRA_IDENTITY, model.getIdentity());
                intent.putExtra(NewStudentActivity.EXTRA_EMAIL, model.getEmail());
                intent.putExtra(NewStudentActivity.EXTRA_GENDER, model.getGender());
                intent.putExtra(NewStudentActivity.EXTRA_BIRTH, model.getBirth());
                intent.putExtra(NewStudentActivity.EXTRA_PAYING, model.getPaying());
                startActivityForResult(intent, EDIT_STUDENT_REQUEST);

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ADD_STUDENT_REQUEST && resultCode == RESULT_OK) {
            String name = data.getStringExtra(NewStudentActivity.EXTRA_NAME);
            String identity = data.getStringExtra(NewStudentActivity.EXTRA_IDENTITY);
            String email = data.getStringExtra(NewStudentActivity.EXTRA_EMAIL);
            String gender = data.getStringExtra(NewStudentActivity.EXTRA_GENDER);
            String birth = data.getStringExtra(NewStudentActivity.EXTRA_BIRTH);
            String paying = data.getStringExtra(NewStudentActivity.EXTRA_PAYING);


            StudentModal model = new StudentModal(name, identity, email, gender, birth,  paying);
            viewmodalstudent.insert(model);
            Toast.makeText(this, "Student saved", Toast.LENGTH_SHORT).show();
        } else if (requestCode == EDIT_STUDENT_REQUEST && resultCode == RESULT_OK) {
            int id = data.getIntExtra(NewStudentActivity.EXTRA_ID, -1);
            if (id == -1) {
                Toast.makeText(this, "Student can't be updated", Toast.LENGTH_SHORT).show();
                return;
            }
            String name = data.getStringExtra(NewStudentActivity.EXTRA_NAME);
            String identity = data.getStringExtra(NewStudentActivity.EXTRA_IDENTITY);
            String email = data.getStringExtra(NewStudentActivity.EXTRA_EMAIL);
            String gender = data.getStringExtra(NewStudentActivity.EXTRA_GENDER);
            String birth = data.getStringExtra(NewStudentActivity.EXTRA_BIRTH);
            String paying = data.getStringExtra(NewStudentActivity.EXTRA_PAYING);


            StudentModal model = new StudentModal(name, identity, email, gender, birth,   paying);
            model.setId(id);
            viewmodalstudent.update(model);
            Toast.makeText(this, "Student updated", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Student not saved", Toast.LENGTH_SHORT).show();
        }

    }
}