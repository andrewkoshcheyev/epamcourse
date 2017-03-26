package com.koshcheyev.quadrangle.processing;

import com.koshcheyev.quadrangle.entity.Point;
import com.koshcheyev.quadrangle.entity.Quadrangle;

import java.util.ArrayList;

/**
 * Created by Andrew on 21.03.2017.
 */
public class AreaCalculator {

    public static Double calculateArea(Quadrangle quadrangle){
        ArrayList<Point> vertices = quadrangle.getVertices();
        //vertices.add(vertices.get(0));// ПРОВЕРИТЬ СОЗДАННЫЙ СПИСОК!!!
        double sum = 0.0;
        for (int i=0;i<quadrangle.getVerticesCount()-1; i++){
            sum+=(vertices.get(i).getX()+vertices.get(i+1).getY()-vertices.get(i).getY()*vertices.get(i+1).getX());
        }
        return Math.abs(sum/2.0); // ПРОВЕРИТЬ ЕЩЕ РАЗ ФОРМУЛЫ СДЕЛАТЬ ПРОВЕРКУ НА ПРЯМОУГОЛЬНИКЕ
    }

}
