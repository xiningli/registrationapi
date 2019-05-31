package com.xiningli.registrationapi;

import com.xiningli.registrationapi.userprofile.SignupProfile;
import com.xiningli.registrationapi.userprofile.UserRepository;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
public class SignupProfileController {
    @Autowired
    UserRepository userRepository;

    @GetMapping("/user")
    public List<SignupProfile> index(){
        return userRepository.findAll();
    }

    @GetMapping("/user/{id}")
    public SignupProfile show(@PathVariable String id){
        return userRepository.findById(id).get();
    }

    @PostMapping("/user")
    public SignupProfile create(@RequestBody Map<String, String> body) throws Exception{
        SignupProfile sp = new SignupProfile();
        sp.setUserName(body.get("userName"));
        sp.setFirstName(body.get("firstName"));
        sp.setLastName(body.get("lastName"));

        Date dob = null;
        try{
            dob = (new SimpleDateFormat("yyyy-MM-dd").parse(body.get("dob")));
        }catch (Exception e){
            dob = null;
        }

        sp.setDob(dob);
        sp.setPassword(body.get("password"));
        Integer income = null;
        try{
            income =Integer.parseInt(body.get("annualIncome"));
        }catch (NumberFormatException e){
            income = null;
        }
        sp.setAnnualIncome(income);

        Boolean gender = null;

        if (body.get("gender")!=null){
            if (body.get("gender").toLowerCase().equals("male")||body.get("gender").toLowerCase().equals("true")){
                gender = true;
            }
            if (body.get("gender").toLowerCase().equals("female")||body.get("gender").toLowerCase().equals("false")){
                gender = false;
            }
        }

        sp.setGender(gender);

        Integer height = null;
        try{
            height =Integer.parseInt(body.get("height"));
        }catch (NumberFormatException e){
            height = null;
        }


        sp.setHeight(height);
        Integer weight = null;
        try{
            weight =Integer.parseInt(body.get("weight"));
        }catch (NumberFormatException e){
            weight = null;
        }

        sp.setWeight(weight);
        sp.setPersonalMotto(body.get("personalMotto"));
        return userRepository.save(sp);
    }


    @PutMapping("/user/{id}")
    public SignupProfile update(@PathVariable String id, @RequestBody Map<String, String> body) {
        SignupProfile tmp = userRepository.findById(id).get();
        BeanWrapper wrapper = new BeanWrapperImpl(tmp);
        wrapper.setPropertyValues(body);
        SignupProfile result = (SignupProfile) wrapper.getWrappedInstance();
        userRepository.save(result);
        return result;
    }


    @DeleteMapping("user/{id}")
    public boolean delete(@PathVariable String id){
        userRepository.deleteById(id);
        return true;
    }

}
