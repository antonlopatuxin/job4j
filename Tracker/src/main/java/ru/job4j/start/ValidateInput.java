package ru.job4j.start;

import java.io.IOException;

public class ValidateInput extends ConsoleInput {

    @Override
    public int askInt(String question, int[] range) throws IOException {

        boolean invalide = true;
        int value = -1;

        do {
            try {

                value = super.askInt(question, range);
                invalide = false;

            } catch (NumberFormatException nfe) {

                System.out.println("<=====================================>");
                System.out.println("Некорректный ввод.");
                System.out.println("<=====================================>");
            }catch (MenuOutException moe){

                System.out.println("<=====================================>");
                System.out.println("Некорректный ввод.");
                System.out.println("<=====================================>");
            }

        } while (invalide);

        return value;
    }
}
