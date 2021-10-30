package com.example.hoangkimduc;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnAdd = findViewById(R.id.btnAdd);
        DBManager db = new DBManager(this);
        btnAdd.setOnClickListener(v -> {
            openFragment(CrudFragment.newInstance("", "", "", "add"));
        });
        RecyclerView rvContact = findViewById(R.id.rvContact);
        rvContact.setAdapter(new ContactAdapter(db.getAll(), getSupportFragmentManager()));
        rvContact.setLayoutManager(new LinearLayoutManager(this));

    }

    //open fragments
    private void openFragment(Fragment fragment) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.fragment_container, fragment, "New Fragment");
        transaction.addToBackStack(null);
        transaction.commit();
    }

}