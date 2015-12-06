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

import com.ryctabo.test.entity.Organization;

public class OrganizationFragment extends Fragment {

    EditText editName;
    EditText editAddress;
    EditText editPhone;

    Button btnSave;
    Button btnClean;

    public OrganizationFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_organization, container, false);

        this.editName = (EditText) view.findViewById(R.id.edit_name);
        this.editAddress = (EditText) view.findViewById(R.id.edit_address);
        this.editPhone = (EditText) view.findViewById(R.id.edit_phone_number);

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
        String name = editName.getText().toString();
        String address = editAddress.getText().toString();
        String phone = editPhone.getText().toString();

        Organization organization = new Organization(name, address, phone);

        Log.i("info", organization.toString());
    }

    public void actionClean() {
        Log.i("action", "on click in button clean.");
        this.editName.setText("");
        this.editAddress.setText("");
        this.editPhone.setText("");
    }

}
