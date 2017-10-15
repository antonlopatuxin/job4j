package ru.job4j.strategy;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import static org.junit.Assert.*;

public class PaintTest {

    @Test
    public void triangleTest(){

        Paint paint = new Paint();
        String expected = "   ^" + "\n" + "  ^^^" + "\n" + " ^^^^^" + "\n" + "^^^^^^^";
        assertThat(paint.draw(new Triangle()), is(expected));
    }

    @Test
    public void squareTest(){

        Paint paint = new Paint();
        String expected = "#####" + "\n" + "#   #" + "\n" + "#   #" + "\n" + "#   #" + "\n" + "#####";
        assertThat(paint.draw(new Square()), is(expected));
    }
}