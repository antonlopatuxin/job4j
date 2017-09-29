package ru.job4j.start;

public class StubInput implements Input {
    private String[] answers;
    private int position = 0;

    public StubInput(String[] answers){
        this.answers = answers;
    }

    public String askString(String question){
        return answers[position++];
    }

    public int askInt(String question){
        return Integer.parseInt(answers[position++]);
    }
}
