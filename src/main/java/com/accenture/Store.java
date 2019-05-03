package com.accenture;

import org.springframework.stereotype.Component;

import static com.accenture.IotImage.*;

@Component()
public class Store{
    private String imageUrl;

    public Store(){
        imageUrl = OFF_IMAGE_URL;
    }

    public String getImageUrl(){
        return imageUrl;
    }

    public void setImageUrl(String imageUrl){
        this.imageUrl = imageUrl;
    }
}
