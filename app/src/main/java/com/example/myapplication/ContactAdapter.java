package com.example.myapplication;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ContactAdapter extends RecyclerView.Adapter {
    private final ArrayList<Person> items = new ArrayList<>();

    public ContactAdapter(final ArrayList<Person> i) {
        if (i != null) {
            this.items.addAll(i);
        }
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.contact, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((ViewHolder) holder).bind(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }


    private class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView tvName;
        private final TextView tvAddress;
        private final TextView tvBirthday;

        public ViewHolder(View view) {
            super(view);
            tvName = view.findViewById(R.id.tvName);
            tvAddress = view.findViewById(R.id.tvAddress);
            tvBirthday = view.findViewById(R.id.tvBirthday);
            view.setOnClickListener(v -> {
                openFragment(CrudFragment.newInstance("", "", "", "add"));
            });
        }

        public void bind(final Person person) {
            tvName.setText(person.getName());
            tvAddress.setText(person.getAddress());
            tvBirthday.setText(person.getBirthday());
        }
        //open fragments
//        private void openFragment(Fragment fragment) {
//            FragmentManager fm = context.
//            FragmentTransaction transaction = fm.beginTransaction();
//            transaction.replace(R.id.fragment_container, fragment, "New Fragment");
//            transaction.addToBackStack(null);
//            transaction.commit();
//        }
    }
}
