package com.example.gymmemberapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class AddMemberActivity extends AppCompatActivity {

    EditText nameEdit;
    EditText idEdit;
    EditText planEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_member);

        nameEdit = findViewById(R.id.name_editView);
        idEdit = findViewById(R.id.id_editView);
        planEdit = findViewById(R.id.plan_editView);
    }

    public void onClick(View v) {
        Intent res = new Intent();
        res.putExtra("name", nameEdit.getText().toString().trim());
        res.putExtra("id", idEdit.getText().toString().trim());
        res.putExtra("plan", planEdit.getText().toString().trim());
        setResult(RESULT_OK, res);
        finish();
    }
}
