package com.koshcheyev.quadrangle.processing;

import com.koshcheyev.quadrangle.entity.Quadrangle;

import javax.print.attribute.standard.Sides;
import java.util.ArrayList;

/**
 * Created by Andrew on 21.03.2017.
 */
public class PerimeterCalculator {

    public static double calculatePerimeter(Quadrangle o){
        return calculatePerimeter(o.getSides());
    }

    public static double calculatePerimeter(ArrayList<Double> sides){
        // A separate method for calculating perimeter from array of sides is needed for the validation method
        double sum=0.0;
        for (Double side : sides){
            sum+=side;
        }
        return sum;
    }
}
