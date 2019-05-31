package com.xiningli.registrationapi.hello;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // so that the spring framework would know it is a controller
//from the springMVC
public class HelloController {
    @RequestMapping("/hello")// tells the spring framework whenever the GET is
    // executed, we will run the following
    public String sayHi(){
        return "Hi";
    }
}
