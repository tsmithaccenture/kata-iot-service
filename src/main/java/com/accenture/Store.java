package com.accenture;

class Store{
    private static String CURRENT_IMAGE_URL;

    static String getImageUrl(){
        return CURRENT_IMAGE_URL;
    }

    static void setImageUrl(String imageUrl){
        CURRENT_IMAGE_URL = imageUrl;
    }
}
