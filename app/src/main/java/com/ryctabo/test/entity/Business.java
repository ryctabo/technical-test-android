package com.ryctabo.test.entity;

import java.util.Date;

public class Business {

    private String title;

    private String description;

    private Organization organization;

    private Person person;

    private float value;

    private Date closeDate;

    private String state;

    public Business() {
    }

    public Business(String title, String description, Organization organization, Person person,
                    float value, Date closeDate, String state) {
        this.title = title;
        this.description = description;
        this.organization = organization;
        this.person = person;
        this.value = value;
        this.closeDate = closeDate;
        this.state = state;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public Date getCloseDate() {
        return closeDate;
    }

    public void setCloseDate(Date closeDate) {
        this.closeDate = closeDate;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return String.format("bussines[%s, %s, %f]", title, person.getName(), value);
    }
}
