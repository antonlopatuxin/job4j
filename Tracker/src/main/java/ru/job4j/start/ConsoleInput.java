package ru.job4j.start;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import ru.job4j.start.*;

public class ConsoleInput implements Input {
    // Не удалось мне укротить непослушный Scanner, поэтому используем BufferedReader
    private BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    String inString;

    // Ввод строковых параметров
    public String askString(String question) throws IOException {
        System.out.print(question);

      return input.readLine();
    }
    // Ввод целочисленных параметров
    public int askInt(String question) throws IOException {
        System.out.print(question);
        inString = input.readLine();

        return Integer.parseInt(inString);
    }
}
