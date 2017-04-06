package com.bsk.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OfferingController {

    @GetMapping("/offering")
    public String offeringPage(){
        return "offering";
    }
}
