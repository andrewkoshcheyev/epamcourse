package com.koshcheyev.quadrangle.processing;

import com.koshcheyev.quadrangle.entity.Point;
import com.koshcheyev.quadrangle.entity.Quadrangle;
import com.koshcheyev.quadrangle.inputprocessing.DataReader;
import com.koshcheyev.quadrangle.observer.QuadrangleDataObserver;
import com.koshcheyev.quadrangle.processing.QuadragleCalculations;
import org.hamcrest.core.Every;
import org.hamcrest.number.IsCloseTo;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

/**
 * Created by Andrew on 21.03.2017.
 */


@RunWith(Parameterized.class)
public class QuadrangleTaskTests {

    private Point a,b,c,d;
    private Quadrangle quadrangle;
    private static QuadrangleDataObserver dataObserver;

    public QuadrangleTaskTests(Point a, Point b, Point c, Point d) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        quadrangle = new Quadrangle(a,b,c,d);
    }

    @Parameters public static Collection<Object[]> quadrangleVerticesSets() {// Receiving data from file
        String path = "resources/input.txt";
        DataReader tempReader = new DataReader(path);
        ArrayList<ArrayList<Point>> objectsVertices = tempReader.getObjectsVertices();

       Object[][] quadrangleVerticesSets = new Object[objectsVertices.size()][4];
       for (int i=0; i<objectsVertices.size();i++){
           quadrangleVerticesSets[i] = new Object[] {
                   objectsVertices.get(i).get(0),
                   objectsVertices.get(i).get(1),
                   objectsVertices.get(i).get(2),
                   objectsVertices.get(i).get(3)};
       }
       return Arrays.asList(quadrangleVerticesSets);
    }


    @Before // Validating the quadrangle before running each test and creating data observer if passed
    public void quadrangleValidation() {
        Assert.assertTrue(QuadragleCalculations.validate(quadrangle.getVertices()));
        dataObserver = new QuadrangleDataObserver(quadrangle);
    }


    @Test
    public void isQuadrangleTest (){
        Assert.assertTrue(true);// Is already validated
        quadrangle.logData();
        dataObserver.logData();
    }

    @Test
    public void isConvexTest (){
        Assert.assertEquals(360, QuadragleCalculations.calculateAngleSum(quadrangle.getVertices()), 0.1);
        quadrangle.logData();
        dataObserver.logData();
    }

    @Test
    public void isSquareTest (){
        Iterable<Double> angles = QuadragleCalculations.calculateAngles(quadrangle.getVertices());
        Assert.assertThat(angles, (Every.everyItem(new IsCloseTo(90, 1.5))));
        quadrangle.logData();
        dataObserver.logData();
    }


}
