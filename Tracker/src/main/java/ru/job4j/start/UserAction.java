package ru.job4j.start;

import ru.job4j.tracker.Tracker;

import java.io.IOException;

public interface UserAction {

    int key(); // возвращает кнопку меню

    void execute(Input input, Tracker tracker) throws IOException; // метод основных действий

    String info(); // возвращает описание действий
}
