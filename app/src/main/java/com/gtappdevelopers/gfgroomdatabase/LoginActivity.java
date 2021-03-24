package com.gtappdevelopers.gfgroomdatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    EditText nameEdit, passwordEdit;
    Button submitBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initViews();
        setListeners();
    }

    private void initViews() {
        nameEdit = findViewById(R.id.et_username);
        passwordEdit = findViewById(R.id.et_password);
        submitBtn = findViewById(R.id.bt_submit);
    }

    private void setListeners() {
        submitBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if ((nameEdit.getText().toString().equals("admin") &&
                passwordEdit.getText().toString().equals("admin"))) {
            AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
            builder.setIcon(R.drawable.ic_check);
            builder.setTitle("Login Successfully !!!");
            builder.setMessage("Welcome");
            Intent intent =new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            builder.setNegativeButton("YES", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            AlertDialog alertDialog = builder.create();
            alertDialog.show();

        } else {
            Toast.makeText(getApplicationContext(), "Invalid Username & Password", Toast.LENGTH_SHORT).show();
        }


    }


}