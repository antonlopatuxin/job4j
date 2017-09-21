package ru.job4j.tracker;

public class Item {

    private String id;
    private String name;
    private String description;
    private String create;

    public Item(){

    }

    public Item(String name, String description, String create){

        this.name = name;
        this.description = description;
        this.create = create;
    }

    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getDescription(){
        return this.description;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public String getCreate(){
        return this.create;
    }

    public void setCreate(String create){
        this.create = create;
    }

    public void setId(String id){
        this.id = id;
    }

    public String getId(){
        return this.id;
    }
}
