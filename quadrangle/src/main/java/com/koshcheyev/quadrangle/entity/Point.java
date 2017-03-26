package com.koshcheyev.quadrangle.entity;

/**
 * Created by Andrew on 21.03.2017.
 */
public class Point {
    private double x,y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() { return y; }

    public void setX(double x) { this.x = x; }

    public void setY(double y) { this.y = y; }

    @Override
    public String toString() {
        return "(x = " + x + ", y = " + y + ')';
    }
}
