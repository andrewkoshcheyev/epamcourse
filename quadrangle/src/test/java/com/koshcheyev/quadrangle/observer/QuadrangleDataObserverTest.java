package com.koshcheyev.quadrangle.observer;

import com.koshcheyev.quadrangle.entity.Point;
import com.koshcheyev.quadrangle.entity.Quadrangle;
import org.junit.Test;

/**
 * Created by Andrew on 26.03.2017.
 */
public class QuadrangleDataObserverTest {
    @Test
    public void dataObserverTest() {
        Quadrangle quadrangle = new Quadrangle(new Point(0,0),
                new Point(10,0),
                new Point(7,5),
                new Point(3,5));
        QuadrangleDataObserver dataObserver = new QuadrangleDataObserver(quadrangle);
        dataObserver.logData();
        quadrangle.setVertices(new Point(0,0),
                new Point(5,0),
                new Point(5,5),
                new Point(0,5));
        dataObserver.logData();
    }

}