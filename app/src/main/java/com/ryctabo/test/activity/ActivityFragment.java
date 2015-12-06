package com.ryctabo.test.activity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;

import com.ryctabo.test.entity.Activity;
import com.ryctabo.test.entity.Business;
import com.ryctabo.test.entity.Organization;
import com.ryctabo.test.entity.Person;
import com.ryctabo.test.service.MyService;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.List;

public class ActivityFragment extends Fragment{

    EditText editDescription;
    EditText editType;
    EditText editDate;
    EditText editTime;

    Spinner spnOrganization;
    Spinner spnPerson;
    Spinner spnBusiness;

    Button btnSave;
    Button btnClean;

    private Calendar calendar = Calendar.getInstance();

    public ActivityFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_acitivity, container, false);

        editDescription = (EditText) view.findViewById(R.id.edit_description);
        editType = (EditText) view.findViewById(R.id.edit_type);
        editDate = (EditText) view.findViewById(R.id.edit_date);
        editTime = (EditText) view.findViewById(R.id.edit_time);

        editDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actionDate();
            }
        });

        editTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actionTime();
            }
        });

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

        spnOrganization = (Spinner) view.findViewById(R.id.spn_organization);
        spnPerson = (Spinner) view.findViewById(R.id.spn_person);
        spnBusiness = (Spinner) view.findViewById(R.id.spn_business);

        List<Organization> organizations = MyService.getOrganization();
        List<Person> people = MyService.getPeople();
        List<Business> business = MyService.getBusiness();

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

        String[] arrayBusiness = new String[business.size()];
        for (int i = 0; i < business.size(); i++) {
            arrayBusiness[i] = business.get(i).getTitle();
        }

        spnBusiness.setAdapter(getAdapter(arrayBusiness));

        return view;
    }

    private ArrayAdapter<String> getAdapter(String[] array) {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(),
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

                return new DatePickerDialog(getActivity(), dateListener, year, month, day);
            }
        };

        fragment.show(getActivity().getSupportFragmentManager(), "datePicker");
    }

    private void actionTime() {
        Log.i("action", "clicked edit text!");

        DialogFragment fragment = new DialogFragment() {
            @NonNull
            @Override
            public Dialog onCreateDialog(Bundle savedInstanceState) {
                final Calendar instance = Calendar.getInstance();

                int hourOfDay = instance.get(Calendar.HOUR_OF_DAY);
                int minute = instance.get(Calendar.MINUTE);

                return new TimePickerDialog(getActivity(), timeListener, hourOfDay, minute, false);
            }
        };

        fragment.show(getActivity().getSupportFragmentManager(), "timePicker");
    }

    DatePickerDialog.OnDateSetListener dateListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, monthOfYear);
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

            DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.LONG);
            String dateString = dateFormat.format(calendar.getTime());

            editDate.setText(dateString);
        }
    };

    TimePickerDialog.OnTimeSetListener timeListener = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
            calendar.set(Calendar.MINUTE, minute);

            DateFormat format = DateFormat.getTimeInstance(DateFormat.SHORT);
            String timeString = format.format(calendar.getTime());

            editTime.setText(timeString);
        }
    };

    private void actionClean() {
        Log.i("action", "on click in button clean.");
        editDescription.setText("");
        editType.setText("");
        editDate.setText("");
        editTime.setText("");

        spnBusiness.setSelection(0);
        spnOrganization.setSelection(0);
        spnPerson.setSelection(0);
    }

    private void actionSave() {
        Log.i("action", "on click in button save.");
        String description = editDescription.getText().toString();
        String type = editType.getText().toString();
        Organization organization = MyService.getOrganization().get(spnOrganization.getSelectedItemPosition());
        Person person = MyService.getPeople().get(spnPerson.getSelectedItemPosition());
        Business business = MyService.getBusiness().get(spnBusiness.getSelectedItemPosition());

        Activity activity = new Activity(description, type, organization, person, business, calendar);
        Log.i("info", activity.toString());
    }

}
