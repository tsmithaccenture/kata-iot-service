package com.accenture;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.annotation.Resource;

import static com.accenture.IotImage.*;

@Controller
public class LightController{

    @Resource
    private Store store;

    @GetMapping("/home")
    public String homePage(Model model){
        model.addAttribute("imageUrl", store.getImageUrl());
        return "home";
    }

    @GetMapping("/imageurl")
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody String imageUrl(){
        return store.getImageUrl();
    }

    @GetMapping("/turn/on")
    @ResponseStatus(value = HttpStatus.OK)
    public void on(){
        store.setImageUrl(ON_IMAGE_URL);
    }

    @GetMapping("/turn/off")
    @ResponseStatus(value = HttpStatus.OK)
    public void off(){
        store.setImageUrl(OFF_IMAGE_URL);
    }
}
