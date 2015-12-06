package com.ryctabo.test.activity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.ryctabo.test.entity.Business;
import com.ryctabo.test.entity.Organization;
import com.ryctabo.test.entity.Person;
import com.ryctabo.test.service.MyService;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class BusinessFragment extends Fragment {

    EditText editTitle;
    EditText editDescription;
    EditText editValue;
    EditText editState;
    EditText editDate;

    Spinner spnOrganization;
    Spinner spnPerson;

    Button btnSave;
    Button btnClean;

    private Calendar calendar;

    public BusinessFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_business, container, false);

        editDescription = (EditText) view.findViewById(R.id.edit_description);
        editTitle = (EditText) view.findViewById(R.id.edit_title);
        editValue = (EditText) view.findViewById(R.id.edit_value);
        editState = (EditText) view.findViewById(R.id.edit_state);
        editDate = (EditText) view.findViewById(R.id.edit_date);

        editDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actionDate();
            }
        });

        spnOrganization = (Spinner) view.findViewById(R.id.spn_organization);
        spnPerson = (Spinner) view.findViewById(R.id.spn_person);

        btnSave = (Button) view.findViewById(R.id.btnSave);
        btnClean = (Button) view.findViewById(R.id.btnClean);
        
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actionSave();
            }
        });
        
        btnClean.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actionClean();
            }
        });

        List<Organization> organizations = MyService.getOrganization();
        List<Person> people = MyService.getPeople();

        String[] arrayOrg = new String[organizations.size()];
        for (int i = 0; i < organizations.size(); i++) {
            arrayOrg[i] = organizations.get(i).getName();
        }

        spnOrganization.setAdapter(getAdapter(arrayOrg));

        String[] arrayPeople = new String[people.size()];
        for (int i = 0; i < people.size(); i++) {
            arrayPeople[i] = people.get(i).getName();
        }

        spnPerson.setAdapter(getAdapter(arrayPeople));

        return view;
    }

    private ArrayAdapter<String> getAdapter(String[] array) {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_spinner_item, array);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        return adapter;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    private void actionClean() {
        Log.i("action", "on click in button clean.");
        editTitle.setText("");
        editDescription.setText("");
        editValue.setText("");
        editState.setText("");
        editDate.setText("");

        calendar = null;

        spnOrganization.setSelection(0);
        spnPerson.setSelection(0);
    }

    private void actionSave() {
        Log.i("action", "on click in button save.");
        String title = editTitle.getText().toString();
        String description = editDescription.getText().toString();
        float value = Float.valueOf(editValue.getText().toString());
        String state = editState.getText().toString();
        Organization organization = MyService.getOrganization().get(spnOrganization.getSelectedItemPosition());
        Person person = MyService.getPeople().get(spnPerson.getSelectedItemPosition());
        Date date = calendar.getTime();

        Business business = new Business(title, description, organization, person, value, date, state);

        Log.i("info", business.toString());
    }

    private void actionDate() {
        Log.i("action", "clicked edit text!");

        DialogFragment fragment = new DialogFragment() {
            @NonNull
            @Override
            public Dialog onCreateDialog(Bundle savedInstanceState) {
                final Calendar instance = Calendar.getInstance();
                int year = instance.get(Calendar.YEAR);
                int month = instance.get(Calendar.MONTH);
                int day = instance.get(Calendar.DAY_OF_MONTH);

                return new DatePickerDialog(getActivity(), listener, year, month, day);
            }
        };

        fragment.show(getActivity().getSupportFragmentManager(), "datePicker");
    }

    DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            final Calendar instance = Calendar.getInstance();
            instance.set(Calendar.YEAR, year);
            instance.set(Calendar.MONTH, monthOfYear);
            instance.set(Calendar.DAY_OF_MONTH, dayOfMonth);

            DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.LONG);
            String dateString = dateFormat.format(instance.getTime());

            editDate.setText(dateString);

            calendar = instance;
        }
    };

}
