package com.koshcheyev.quadrangle.processing;

import com.koshcheyev.quadrangle.entity.Point;
import com.koshcheyev.quadrangle.entity.Quadrangle;

import java.util.ArrayList;

/**
 * Created by Andrew on 21.03.2017.
 */
public class AreaCalculator {

    public static Double calculateArea(Quadrangle quadrangle){
        Point p1 = quadrangle.getVertices().get(0);
        Point p2 = quadrangle.getVertices().get(1);
        Point p3 = quadrangle.getVertices().get(2);
        Point p4 = quadrangle.getVertices().get(3);
        double square = Math.abs((p2.getY() - p4.getY()) * (p3.getX() - p1.getX()) +
                (p1.getY() - p3.getY()) * (p2.getX() - p4.getX()))*0.5;
        return square;
        /*ArrayList<Point> vertices = quadrangle.getVertices();
        double sum = 0.0;
        for (int i=0;i<quadrangle.getVerticesCount()-1; i++){
            sum+=(vertices.get(i).getX()+vertices.get(i+1).getY()-vertices.get(i).getY()*vertices.get(i+1).getX());
        }
        return Math.abs(sum/2.0); // ПРОВЕРИТЬ ЕЩЕ РАЗ ФОРМУЛЫ СДЕЛАТЬ ПРОВЕРКУ НА ПРЯМОУГОЛЬНИКЕ*/
    }

}
