package ru.job4j.start;

import ru.job4j.strategy.Paint;
import ru.job4j.strategy.Square;
import ru.job4j.strategy.Triangle;
import ru.job4j.tracker.Item;
import ru.job4j.tracker.Tracker;

import java.io.IOException;

public class StartUI {

    private Input input;
    private Tracker tracker;
    private int[] range = new int[] {0, 1, 2, 3, 4, 5, 6};

    public StartUI(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    public void init() throws IOException {

        MenuTracker menu = new MenuTracker(input, tracker);
        menu.fillActions();

        do{

            menu.show();
            int key = input.askInt("Введите пункт меню: ", this.range);
            menu.select(key);

        } while(!"Да".equals(menu.getExit()));

    }

    public static void main(String[] args) throws IOException {

        StartUI start = new StartUI(new ValidateInput(), new Tracker());
        start.init();
    }
}