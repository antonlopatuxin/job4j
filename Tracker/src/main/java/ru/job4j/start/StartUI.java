package ru.job4j.start;

import ru.job4j.tracker.Item;
import ru.job4j.tracker.Tracker;

import java.io.IOException;

public class StartUI {

    private Input input;
    private Tracker tracker;
    private Menu menu;

    public StartUI(Input input) {
        this.input = input;
       // this.menu = new Menu();
    }

    public String init() {
        String name = null;
        Tracker tracker = new Tracker();
      //  tracker.add()

        for (Item item : tracker.findAll()) {
            name = item.getName();

        }
        return name;
    }

    public static void main(String[] args) throws IOException {

        Menu menuTracker = new Menu();
        menuTracker.menuAction();
    }
}