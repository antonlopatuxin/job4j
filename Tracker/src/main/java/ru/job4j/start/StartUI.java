package ru.job4j.start;

import ru.job4j.tracker.Item;
import ru.job4j.tracker.Tracker;

import java.io.IOException;

public class StartUI {

//    private Input input;
//    private Tracker tracker;
//
////    public StartUI(Input input) {
////        this.input = input;
//       // this.menu = new Menu();
//   // }
//
//    public void init() {
//        String ask = input.askString("Enter a number: ");
//        switch(ask){
//
//        }
//
//    }

    public static void main(String[] args) throws IOException {

        Tracker tracker = new Tracker();
        String[] array = new String[5];
        Input input = new ConsoleInput();
        Menu start = new Menu(tracker, input);
        start.menuAction();
    }
}