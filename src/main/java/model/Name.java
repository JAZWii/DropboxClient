package model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Name {
    @JsonProperty("given_name")
    private String givenName;

    @JsonProperty("surname")
    private String surname;

    @JsonProperty("familiar_name")
    private String familiarName;

    @JsonProperty("display_name")
    private String displayName;

    @JsonProperty("abbreviated_name")
    private String abbreviatedName;

    public Name() {
    }

    public Name(String givenName, String surname, String familiarName, String displayName, String abbreviatedName) {
        this.givenName = givenName;
        this.surname = surname;
        this.familiarName = familiarName;
        this.displayName = displayName;
        this.abbreviatedName = abbreviatedName;
    }

    public String getGivenName() {
        return givenName;
    }

    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getFamiliarName() {
        return familiarName;
    }

    public void setFamiliarName(String familiarName) {
        this.familiarName = familiarName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getAbbreviatedName() {
        return abbreviatedName;
    }

    public void setAbbreviatedName(String abbreviatedName) {
        this.abbreviatedName = abbreviatedName;
    }
}
