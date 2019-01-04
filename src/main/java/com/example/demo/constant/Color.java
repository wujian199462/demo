package com.example.demo.constant;

public enum Color {
    Red("1");
    private String colorType;

    Color(String colorType){this.colorType=colorType;}
    public String getValue(){return colorType;}

}
