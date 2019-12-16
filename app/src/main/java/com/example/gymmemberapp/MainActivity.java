package com.example.gymmemberapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements GymMemberRVAdapter.Callback {

    ImageButton searchButton;
    EditText searchText;
    RecyclerView memberRecycler;

    ArrayList<GymMember>  members;
    GymMemberDatabaseHelper databaseHelper;

    GymMemberRVAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        searchText = findViewById(R.id.member_search_text);
        searchButton = findViewById(R.id.searchButton);
        memberRecycler = findViewById(R.id.memberRecycler);

        databaseHelper = new GymMemberDatabaseHelper(this, null, null, 1);
        members = databaseHelper.getMembers();

        adapter = new GymMemberRVAdapter(members, this);

        memberRecycler.setAdapter(adapter);
        //DividerItemDecoration itemDecoration = new DividerItemDecoration(this, RecyclerView.VERTICAL);
        //memberRecycler.addItemDecoration(itemDecoration);

        LinearLayoutManager manager = new LinearLayoutManager(this);
        memberRecycler.setLayoutManager(manager);

        Toast.makeText(this, "Count: " + members.size(), Toast.LENGTH_LONG).show();

        searchText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                members = databaseHelper.getMembers(s.toString().trim());
                adapter.changeMembers(members);
                adapter.notifyDataSetChanged();
            }
        });
    }

    int requestCode = 500;

    @Override
    public void entryClicked(GymMember member) {
        if(member.nameString.equals("Add new member")) {
            Intent intent = new Intent(this, AddMemberActivity.class);
            startActivityForResult(intent, requestCode);
        }
        else {
            Intent intent = new Intent(this, DisplayMemberActivity.class);
            intent.putExtra("name", member.nameString);
            intent.putExtra("id", member.idString);
            intent.putExtra("plan", member.planString);
            startActivity(intent);
        }
    }

    @Override
    protected void onActivityResult(int requestcode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestcode, resultCode, data);
        if(requestcode == this.requestCode && resultCode == RESULT_OK) {
            GymMember mem = new GymMember(data.getStringExtra("name"), data.getStringExtra("id"), data.getStringExtra("plan"));
            databaseHelper.insertMember(mem);
            members.add(mem);
            adapter.changeMembers(members);
            adapter.notifyDataSetChanged();
        }
    }
}


