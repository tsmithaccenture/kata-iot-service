package com.accenture;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LightController {

    @GetMapping("/on")
    public String on(Model model){
        model.addAttribute("imageUrl",
                "https://www.roohanrealty.com/wp-content/uploads/2017/02/light-bulb-768x768.png");
        return "light";
    }
}
