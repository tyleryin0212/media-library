package com.tyleryin.medialibrary.in_memory_domain;

/**
 * The class represents the first name and last name for possible individuals such as authors and recording artists.
 */
public class Name {
    private String firstName;
    private String lastName;

    public Name(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
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

    /**
     *
     * @return the full name of the individual as a string
     */
    public String getFullName() {
        return firstName + " " + lastName;
    }
}
