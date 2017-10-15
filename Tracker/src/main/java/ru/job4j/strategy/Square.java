package ru.job4j.strategy;

public class Square implements Shape{

    public String pic(){
        StringBuilder square = new StringBuilder();
        square.append("#####");
        square.append("\n");
        square.append("#   #");
        square.append("\n");
        square.append("#   #");
        square.append("\n");
        square.append("#   #");
        square.append("\n");
        square.append("#####");
        return square.toString();
    }
}
