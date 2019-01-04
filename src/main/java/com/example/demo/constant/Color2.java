package com.example.demo.constant;

public enum Color2 {
    RED("红",1);

    private String name;
    private int index;

    private Color2(String name,int index){
        this.name = name;
        this.index =index;
    }

    private String getName(){
        return name;
    }
    private int getIndex(){
        return index;
    }
    

}
