package com.ryctabo.test.service;

import com.ryctabo.test.entity.Business;
import com.ryctabo.test.entity.Organization;
import com.ryctabo.test.entity.Person;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MyService {

    public static List<Organization> getOrganization() {
        List<Organization> organizations = new ArrayList<>();

        organizations.add(new Organization("NativApps", "Address", "5555555555"));
        organizations.add(new Organization("Example Organization", "Address", "5555555555"));

        return organizations;
    }

    public static List<Person> getPeople() {
        List<Person> people = new ArrayList<>();

        people.add(new Person("Gustavo Pacheco", "3215856815", "ryctabo@gmail.com"));
        people.add(new Person("Juan Perez", "5555555", "juan@perez.com"));

        return people;
    }

    public static List<Business> getBusiness() {
        List<Business> business = new ArrayList<>();

        Business business1 = new Business("Business1", "description...", getOrganization().get(0),
                getPeople().get(0), 0f, new Date(), "state");
        Business business2 = new Business("Business2", "description...", getOrganization().get(1),
                getPeople().get(1), 0f, new Date(), "state");

        business.add(business1);
        business.add(business2);

        return business;
    }

}
