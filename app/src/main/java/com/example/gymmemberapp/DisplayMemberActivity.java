package com.example.gymmemberapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class DisplayMemberActivity extends AppCompatActivity {

    TextView nameText;
    TextView idText;
    TextView planText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_member);

        nameText = findViewById(R.id.name_editView);
        idText = findViewById(R.id.id_editView);
        planText = findViewById(R.id.plan_editView);

        Intent intent = getIntent();
        nameText.setText(intent.getStringExtra("name"));
        idText.setText(intent.getStringExtra("id"));
        planText.setText(intent.getStringExtra("plan"));

    }
}
