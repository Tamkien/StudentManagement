package com.example.hoangkimduc;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

import com.example.myapplication.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CrudFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CrudFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String ARG_PARAM3 = "param3";
    private static final String ARG_PARAM4 = "param4";

    // TODO: Rename and change types of parameters
    private String name;
    private String address;
    private String birthday;
    private String type;

    public CrudFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param name    Parameter 1.
     * @param address Parameter 2.
     * @return A new instance of fragment CrudFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CrudFragment newInstance(String name, String address, String birthday, String type) {
        CrudFragment fragment = new CrudFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, name);
        args.putString(ARG_PARAM2, address);
        args.putString(ARG_PARAM3, birthday);
        args.putString(ARG_PARAM4, type);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_crud, container, false);
        DBManager db = new DBManager(getContext());
        Button btnSave = view.findViewById(R.id.btnSave);
        Button btnDelete = view.findViewById(R.id.btnDelete);
        EditText etName = view.findViewById(R.id.edtName);
        EditText etAddress = view.findViewById(R.id.edtAddress);
        EditText etBirthday = view.findViewById(R.id.edtBirthday);
        if (getArguments() != null) {
            name = getArguments().getString(ARG_PARAM1);
            address = getArguments().getString(ARG_PARAM2);
            birthday = getArguments().getString(ARG_PARAM3);
            type = getArguments().getString(ARG_PARAM4);
        }
        etName.setText(name);
        etAddress.setText(address);
        etBirthday.setText(birthday);
        if (type.equals("add")) {
            btnDelete.setVisibility(View.GONE);
            btnSave.setOnClickListener(v -> {
                db.add(new Person(etName.getText().toString(), etAddress.getText().toString(), etBirthday.getText().toString()));
                Intent intent = new Intent(getContext(), MainActivity.class);
                startActivity(intent);
            });
        } else {
            btnDelete.setVisibility(View.VISIBLE);
            btnSave.setOnClickListener(v -> {
                db.update(new Person(etName.getText().toString(), etAddress.getText().toString(), etBirthday.getText().toString()));
                Intent intent = new Intent(getContext(), MainActivity.class);
                startActivity(intent);
            });
            btnDelete.setOnClickListener(v -> {
                        db.delete(new Person(etName.getText().toString(), etAddress.getText().toString(), etBirthday.getText().toString()));
                        Intent intent = new Intent(getContext(), MainActivity.class);
                        startActivity(intent);
                    }
            );
        }
        return view;
    }
}