package com.gtappdevelopers.gfgroomdatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import java.util.Calendar;

import devs.mulham.horizontalcalendar.HorizontalCalendar;
import devs.mulham.horizontalcalendar.utils.HorizontalCalendarListener;

public class NewStudentActivity extends AppCompatActivity {

    private EditText NameEdt, IdentityEdt, EmailEdt, BirthEdt;
    private Button StudentBtn;
    RadioGroup radioGroup,radioGroup1;
    RadioButton radioButton, radioButton1;
    String studentGender,studentPaying;

    public static final String EXTRA_ID ="EXTRA_ID";
    public static final String EXTRA_NAME = "EXTRA_STUDENT_NAME";
    public static final String EXTRA_IDENTITY = "EXTRA_STUDENT_IDENTITY";
    public static final String EXTRA_EMAIL = "EXTRA_STUDENT_EMAIL";
    public static final String EXTRA_GENDER = "EXTRA_STUDENT_GENDER";
    public static final String EXTRA_BIRTH = "EXTRA_STUDENT_BIRTH";
    public static final String EXTRA_PAYING = "EXTRA_STUDENT_PAYING";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_student);
        initViews();

        Button buttonApply = findViewById(R.id.BtnSaveStudent);
        buttonApply.setOnClickListener(new View.OnClickListener() {
            @Override
                public void onClick(View v) {
                    int radioId = radioGroup.getCheckedRadioButtonId();
                    radioButton = findViewById(radioId);
                   studentGender=radioButton.getText().toString();
                  int radioId1 = radioGroup1.getCheckedRadioButtonId();
                    radioButton1 = findViewById(radioId1);
                    studentPaying=radioButton1.getText().toString();

                }
            });

        Intent intent = getIntent();
        if (intent.hasExtra(EXTRA_ID)) {
            NameEdt.setText(intent.getStringExtra(EXTRA_NAME));
            IdentityEdt.setText(intent.getStringExtra(EXTRA_IDENTITY));
            EmailEdt.setText(intent.getStringExtra(EXTRA_EMAIL));
            BirthEdt.setText(intent.getStringExtra(EXTRA_BIRTH));
        }
        StudentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String studentName = NameEdt.getText().toString();
                String studentIdentity = IdentityEdt.getText().toString();
                String studentEmail = EmailEdt.getText().toString();
                String studentBirth = BirthEdt.getText().toString();
                if (studentName.isEmpty() || studentIdentity.isEmpty() || studentEmail.isEmpty() || studentGender.isEmpty() ||
                        studentBirth.isEmpty() || studentPaying.isEmpty()) {
                    Toast.makeText(NewStudentActivity.this, "Please enter the valid student details.", Toast.LENGTH_SHORT).show();
                    return;
                }
                saveStudent(studentName, studentIdentity, studentEmail, studentGender, studentBirth, studentPaying);

            }
        });


    }

    private void saveStudent(String studentName, String studentIdentity, String studentEmail, String studentGender,
                             String studentBirth, String studentPaying) {
        Intent data = new Intent();
        data.putExtra(EXTRA_NAME, studentName);
        data.putExtra(EXTRA_IDENTITY, studentIdentity);
        data.putExtra(EXTRA_EMAIL, studentEmail);
        data.putExtra(EXTRA_GENDER, studentGender);
        data.putExtra(EXTRA_BIRTH, studentBirth);
        data.putExtra(EXTRA_PAYING, studentPaying);

        int id = getIntent().getIntExtra(EXTRA_ID, -1);
        if (id != -1) {
            data.putExtra(EXTRA_ID, id);
        }
        setResult(RESULT_OK, data);
        Toast.makeText(this, "Student has been saved to Room Database. ", Toast.LENGTH_SHORT).show();
    }

    public void checkButton(View v) {
        int radioId = radioGroup.getCheckedRadioButtonId();
        radioButton = findViewById(radioId);
        studentGender=radioButton.getText().toString();

    }
    public void checkButton1(View v) {
        int radioId1 = radioGroup1.getCheckedRadioButtonId();
        radioButton1 = findViewById(radioId1);
        studentPaying=radioButton1.getText().toString();

    }
    private void initViews() {
        NameEdt = findViewById(R.id.EdtName);
        IdentityEdt = findViewById(R.id.EdtIdentity);
        EmailEdt = findViewById(R.id.EdtEmail);
        BirthEdt = findViewById(R.id.EdtBirth);
        StudentBtn = findViewById(R.id.BtnSaveStudent);
        radioGroup1 = findViewById(R.id.radioGroup1);
        radioGroup = findViewById(R.id.radioGroup);
    }

}