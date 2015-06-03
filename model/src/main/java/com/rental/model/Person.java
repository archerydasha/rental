package com.rental.model;

import java.math.BigInteger;
import java.util.Date;
import java.util.Objects;

/**
 * Created by Dasha on 27.05.15.
 */
public class Person {
    private int id;
    private String firstName;
    private String lastName;
    private String middleName;
    private String email;
    private Date registrationDate;
    private String phone;

    public Person(int id, String firstName, String lastName, String middleName, String email, Date registrationDate, String phone) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.email = email;
        this.registrationDate = registrationDate;
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Person person = (Person) o;

        return Objects.equals(id, person.id) && Objects.equals(email, person.email)
                && Objects.equals(firstName, person.firstName) && Objects.equals(lastName, person.lastName)
                && Objects.equals(middleName, person.middleName) && Objects.equals(phone, person.phone)
                && Objects.equals(registrationDate, person.registrationDate);
    }

    @Override
    public int hashCode() {
      return Objects.hash(id, email,firstName,lastName,middleName,phone,registrationDate);
    }
}
