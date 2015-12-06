package com.ryctabo.test.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.ryctabo.test.entity.Person;

public class PersonFragment extends Fragment {

    EditText editFullName;
    EditText editPhone;
    EditText editEmail;

    Button btnSave;
    Button btnClean;

    public PersonFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_person, container, false);

        this.editFullName = (EditText) view.findViewById(R.id.edit_full_name);
        this.editPhone = (EditText) view.findViewById(R.id.edit_phone_number);
        this.editEmail = (EditText) view.findViewById(R.id.edit_email);

        this.btnSave = (Button) view.findViewById(R.id.btnSave);
        this.btnClean = (Button) view.findViewById(R.id.btnClean);

        this.btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actionSave();
            }
        });

        this.btnClean.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actionClean();
            }
        });

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    public void actionSave() {
        Log.i("action", "on click in button save.");
        String fullName = this.editFullName.getText().toString();
        String phone = this.editPhone.getText().toString();
        String email = this.editEmail.getText().toString();

        Person person = new Person(fullName, phone, email);
        Log.i("info", person.toString());
    }

    public void actionClean() {
        Log.i("action", "on click in button clean.");
        this.editEmail.setText("");
        this.editFullName.setText("");
        this.editPhone.setText("");
    }

}
