package com.koshcheyev.quadrangle.processing;

import com.koshcheyev.quadrangle.entity.Point;
import com.koshcheyev.quadrangle.entity.Quadrangle;
import com.koshcheyev.quadrangle.entity.SimpleVector;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Admin on 23.03.2017.
 */
public class QuadragleCalculations {

    private static final Logger LOGGER = Logger.getLogger(QuadragleCalculations.class);

    public static boolean validate(ArrayList<Point> vertices) {
        // ПРОВЕРИТЬ 1 УСЛОВИЕ ПО ФОРМУЛЕ
        if (vertices.size() == Quadrangle.getVerticesCount()) {
            boolean firstCondition = !(((vertices.get(0).getX() - vertices.get(2).getX()) * (vertices.get(1).getY() - vertices.get(2).getY())
                    == (vertices.get(1).getX() - vertices.get(2).getX()) * (vertices.get(0).getY() - vertices.get(2).getY()))
                    && ((vertices.get(1).getX() - vertices.get(3).getX()) * (vertices.get(2).getY() - vertices.get(3).getY())
                    == (vertices.get(2).getX() - vertices.get(3).getX()) * (vertices.get(1).getY() - vertices.get(3).getY())));// Area of the triangle is not equal to zero

            ArrayList<Double> tempSides = defineSides(vertices);
            double perimeter = PerimeterCalculator.calculatePerimeter(tempSides);
            boolean secondCondition = true; // Each side is smaller than the sum of other sides
            for (Double side : tempSides) {
                if (!(side < (perimeter - side))) {
                    secondCondition = false;
                    break;
                }
            }
            if (!(firstCondition && secondCondition)) {
                LOGGER.debug("Given points cannot form a quadrangle");
                return false;
            } else {
                return true;
            }
        } else {
            LOGGER.debug("Entity initialisation error - incorrect number of vertices passed");
            return false;
        }
    }

    public static boolean validate(Point a, Point b, Point c, Point d) {
        if (a != null && b != null && c != null && d != null) {
            ArrayList<Point> tempVertices = new ArrayList<>(Arrays.asList(a, b, c, d));
            return validate(tempVertices);
        } else {
            LOGGER.debug("Entity initialisation error - incorrect number of vertices passed");
            return false;
        }
    }


    public static ArrayList<Double> defineSides(ArrayList<Point> passedVertices) {// Need of a separate static method is justified by it's application in the validation method
        ArrayList<Double> definedSides = new ArrayList<>();
        SimpleVector side;
        for (int i = 0; i < Quadrangle.getVerticesCount() - 1; i++) {
            side = new SimpleVector(passedVertices.get(i), passedVertices.get(i + 1));
            definedSides.add(side.getMagnitude());
        }
        definedSides.add(new SimpleVector(passedVertices.get(Quadrangle.getVerticesCount() - 1), passedVertices.get(0)).getMagnitude());// Last side
        return definedSides;
    }

    public static ArrayList<Double> calculateAngles(ArrayList<Point> passedVertices){
        ArrayList<SimpleVector> sides = new ArrayList<>();
        ArrayList<Double>  angles = new ArrayList<>();
        SimpleVector side;
        for (int i = 0; i < Quadrangle.getVerticesCount() - 1; i++) {
            side = new SimpleVector(passedVertices.get(i), passedVertices.get(i + 1));
            sides.add(side);
        }
        sides.add(sides.get(0));
        for (int i = 0; i < Quadrangle.getVerticesCount()-1; i++) {
            angles.add(SimpleVector.calculateAngle(sides.get(i), sides.get(i + 1)));
        }
        angles.add(SimpleVector.calculateAngle(sides.get(Quadrangle.getVerticesCount()-1), sides.get(0)));
        //LOGGER.info("Quadrangle angles are: "+angles);
        return angles;
    }

    public static double calculateAngleSum(ArrayList<Point> passedVertices) {
        ArrayList<Double> angles = calculateAngles(passedVertices);
        double sum = angles.stream().mapToDouble(Double::doubleValue).sum();
        //LOGGER.info("Angle sum of the quadrangle is "+sum+" degrees");
        return sum;
    }

}

