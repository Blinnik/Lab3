package com.example.lab3;

public class Element {
    private String id;
    private String name;
    private String time;
    public Element(String id, String name, String time){
        this.id=id;
        this.name=name;
        this.time=time;
    }
    public String getId(){
        return this.id;
    }
    public String getName(){
        return this.name;
    }
    public String getTime(){
        return this.time;
    }
}
