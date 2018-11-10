package com.quaziwerk.sduhrms.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("profile")
public class ProfileController {

    @GetMapping
    public String getName() {
        return "Makhanov Madiyar";
    }

}
