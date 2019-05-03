package com.accenture;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import static com.accenture.IotImage.*;

@Controller
public class LightController{

    @GetMapping("/home")
    public String homePage(Model model){
        model.addAttribute("imageUrl", Store.getImageUrl());
        return "home";
    }

    @GetMapping("/imageurl")
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody String imageUrl(){
        return Store.getImageUrl();
    }

    @GetMapping("/on")
    @ResponseStatus(value = HttpStatus.OK)
    public void on(){
        Store.setImageUrl(ON_IMAGE_URL);
    }

    @GetMapping("/off")
    @ResponseStatus(value = HttpStatus.OK)
    public void off(){
        Store.setImageUrl(OFF_IMAGE_URL);
    }
}
