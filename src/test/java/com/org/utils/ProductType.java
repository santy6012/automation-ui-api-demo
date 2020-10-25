package com.org.utils;

import java.util.Arrays;

public enum ProductType {
    pet("/pet/1"),
    laptop("/laptop");

    private String [] path;

    ProductType(String... path){
        this.path = path;
    }

    public String getApiPath(){
        return Arrays.toString(path);
    }

}
