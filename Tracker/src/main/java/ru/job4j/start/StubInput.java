package ru.job4j.start;

import java.io.IOException;

public class StubInput implements Input {
    private String[] answers;
    private int position = 0;

    public StubInput(String[] answers){
        this.answers = answers;
    }

    public String askString(String question)throws IOException{
        return answers[position++];
    }

    public int askInt(String question)throws IOException{
        return Integer.parseInt(answers[position++]);
    }
}
