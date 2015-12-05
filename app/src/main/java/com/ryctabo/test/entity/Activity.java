package com.ryctabo.test.entity;

import java.text.DateFormat;
import java.util.Calendar;

public class Activity {

    public static int ID = 1;

    private int id;

    private String description;

    private String type;

    private Organization organization;

    private Person person;

    private Bussines bussines;

    private Calendar dateTime;

    public Activity() {
        this.id = Activity.ID++;
    }

    public Activity(String description, String type, Organization organization, Person person,
                    Bussines bussines, Calendar dateTime) {
        this.id = Activity.ID++;
        this.description = description;
        this.type = type;
        this.organization = organization;
        this.person = person;
        this.bussines = bussines;
        this.dateTime = dateTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Bussines getBussines() {
        return bussines;
    }

    public void setBussines(Bussines bussines) {
        this.bussines = bussines;
    }

    public Calendar getDateTime() {
        return dateTime;
    }

    public void setDateTime(Calendar dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        DateFormat dateFormat = DateFormat.getDateTimeInstance();
        String dateString = dateFormat.format(this.dateTime.getTime());
        return String.format("activity[%d, %s, %s]", this.id, person.getName(), dateString);
    }
}
