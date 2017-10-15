package ru.job4j.strategy;

public class Triangle implements Shape{

    public String pic() {
        StringBuilder triangle = new StringBuilder();
        triangle.append("   ^");
        triangle.append("\n");
        triangle.append("  ^^^");
        triangle.append("\n");
        triangle.append(" ^^^^^");
        triangle.append("\n");
        triangle.append("^^^^^^^");
        return triangle.toString();
    }
}
