package com.xiningli.registrationapi.userprofile;


import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestParam;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.lang.IllegalArgumentException;
import java.util.Date;

@Entity
public class SignupProfile {
    @Id
    private String userName;
    private String firstName;
    private String lastName;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date dob;
    private Boolean gender;
    @NotNull
    private String password;
    private Integer height; // in centimeters
    private Integer weight; // in kg
    private Integer annualIncome; // in USD
    private String personalMotto;

    public SignupProfile(){

    }

    public String getUserName() {
        return userName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    @JsonFormat(pattern="yyyy-MM-dd")
    public Date getDob() {
        return dob;
    }

    public Boolean getGender() {
        return gender;
    }

    public String getPassword() {
        return password;
    }

    public Integer getHeight() {
        return height;
    }

    public Integer getWeight() {
        return weight;
    }

    public Integer getAnnualIncome() {
        return annualIncome;
    }

    public String getPersonalMotto() {
        return personalMotto;
    }

    public void setUserName(String userName) {
        if (userName==null) throw new IllegalArgumentException("username cannot be null");
        if (userName.length()<5) throw new IllegalArgumentException("username must be at least 6 digits long");

        this.userName = userName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setDob(@RequestParam("date")@JsonFormat(pattern="yyyy-MM-dd") Date dob) {
        this.dob = dob;
    }

    public void setGender(Boolean gender) {
        this.gender = gender;
    }

    public void setPassword(String password) {
        if (password==null) throw new IllegalArgumentException("password cannot be null");
        if (password.length()<7) throw new IllegalArgumentException("password must be at least 8 digits long");
        this.password = password;
    }

    public void setHeight(Integer height) {
        if (height!=null&&height<40) throw new IllegalArgumentException("impossible height");
        this.height = height;
    }

    public void setWeight(Integer weight) {
        if (weight!=null&&(weight>700||weight<20)) throw new IllegalArgumentException("impossible weight");
        this.weight = weight;
    }

    public void setAnnualIncome(Integer annualIncome) {
        this.annualIncome = annualIncome;
    }

    public void setPersonalMotto(String personalMotto) {
        this.personalMotto = personalMotto;
    }
}
