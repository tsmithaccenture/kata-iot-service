package com.accenture;

import com.accenture.mqtt.LightPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.annotation.Resource;

@Controller
public class LightController{

    @Resource
    private LightPublisher lightPublisher;

    @Resource
    private Store store;

    @GetMapping("/")
    public String base(){
        return "redirect:/home";
    }

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

    @GetMapping("/toggle")
    @ResponseStatus(value = HttpStatus.OK)
    public void toggle() {
        lightPublisher.publish();
    }
}
