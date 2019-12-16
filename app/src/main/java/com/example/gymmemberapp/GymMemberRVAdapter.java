package com.example.gymmemberapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class GymMemberRVAdapter extends RecyclerView.Adapter {

    private List<GymMember> members;
    private Callback callback;

    public interface Callback {
        void entryClicked(GymMember member);
    }

    public GymMemberRVAdapter(List<GymMember> mem, Callback back) {
        members = mem;
        callback = back;
    }

    public void changeMembers(List<GymMember> mem) {
        members = mem;
    }

    @NonNull
    @Override
    public MemberViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.entry_layout, parent, false);
        return new MemberViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        GymMember mem = members.get(position);
        if(mem.nameString.equals("Add new member")) {
            ((MemberViewHolder)holder).entryText.setText(mem.nameString);
        }
        else {
            ((MemberViewHolder) holder).entryText.setText(mem.toString());
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callback.entryClicked(members.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return members.size();
    }

    class MemberViewHolder extends RecyclerView.ViewHolder {

        public TextView entryText;

        public MemberViewHolder(@NonNull View itemView) {
            super(itemView);
            entryText = itemView.findViewById(R.id.entryText);
        }
    }
}
