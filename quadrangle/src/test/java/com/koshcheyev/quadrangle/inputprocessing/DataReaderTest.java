package com.koshcheyev.quadrangle.inputprocessing;

import com.koshcheyev.quadrangle.entity.Point;
import org.junit.Test;

import java.util.ArrayList;

/**
 * Created by Andrew on 26.03.2017.
 */
public class DataReaderTest {
    @Test
    public void getObjectsVertices() {
        DataReader testReader = new DataReader("resources/input.txt");
        ArrayList<ArrayList<Point>> vertices = testReader.getObjectsVertices();
    }

}