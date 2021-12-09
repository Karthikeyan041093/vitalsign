package com.example.i2i.VitalSignManagement.ctrl;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/hello")
public class HelloWorldController {

    @RequestMapping(value="/world", method=RequestMethod.GET)
    public String helloWorld()
    {
        return "HAI THIS IS VITAL SIGN MODULE - HELLO WORLD PAGE !!!!!";
    }
}
