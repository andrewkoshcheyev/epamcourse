package com.koshcheyev.quadrangle.entity;

import org.apache.log4j.Logger;

/**
 * Created by Andrew on 21.03.2017.
 */
public class SimpleVector {

    private double x, y;
    private double magnitude;
    private static final Logger LOGGER = Logger.getLogger(SimpleVector.class);

    public SimpleVector(Point a, Point b) {
        this.x = b.getX() - a.getX();
        this.y = b.getY() - a.getY();
        calculateMagnitude();
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getMagnitude() {
        return magnitude;
    }

    public void calculateMagnitude() {
        this.magnitude = Math.hypot(x, y);
    }

    public static double scalarProd(SimpleVector a, SimpleVector b) {
        return a.getX() * b.getX() + a.getY() * b.getY();
    }

    public static double calculateAngle(SimpleVector a, SimpleVector b) { //exception
        try{
            return Math.toDegrees(Math.acos(Math.toRadians((scalarProd(a, b)) / (a.getMagnitude() * b.getMagnitude()))));
        }catch (ArithmeticException e){
            LOGGER.fatal("Dividing by zero due to the zero magnitude of a vector");
            throw new RuntimeException();
        }
    }

}

