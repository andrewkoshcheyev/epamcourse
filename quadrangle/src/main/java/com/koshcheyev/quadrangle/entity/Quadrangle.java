package com.koshcheyev.quadrangle.entity;

import com.koshcheyev.quadrangle.observer.Observable;
import com.koshcheyev.quadrangle.observer.QuadrangleObserver;
import com.koshcheyev.quadrangle.processing.QuadragleCalculations;
import com.koshcheyev.quadrangle.processing.SimpleIDCounter;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

/**
 * Created by Andrew on 21.03.2017.
 */
public class Quadrangle implements Observable {

    private LinkedList<QuadrangleObserver> observers = new LinkedList<QuadrangleObserver>();
    private ArrayList<Point> vertices;
    private ArrayList<Double> sides;
    private final int id;

    private static final Logger LOGGER = Logger.getLogger(Quadrangle.class);
    private static final int VERTICES_COUNT = 4;

    public Quadrangle(Point a, Point b, Point c, Point d) {
        id = SimpleIDCounter.defineNewID();
        setVertices(a,b,c,d);
        setSides();
    }

    public Quadrangle (ArrayList<Point> points){
        id = SimpleIDCounter.defineNewID();
        setVertices(points);
        setSides();
    }

    public int getID() { return id; }

    public ArrayList<Point> getVertices() { return vertices; }

    public static int getVerticesCount() { return VERTICES_COUNT; }

    public ArrayList<Double> getSides() { return sides; }

    public void setVertices (ArrayList<Point> passedVertices){
        if (passedVertices.size()==VERTICES_COUNT){
            vertices = new ArrayList<>();
            for (Point p : passedVertices){
                this.vertices.add(p);
            }
            setSides();// Changing the sides as well and then notifying observers
        }else{
            LOGGER.fatal("Entity initialisation error - incorrect number of vertices passed into "+this.getClass()+", application stopped working");
            throw new RuntimeException();
        }
    }

    public void setVertices (Point a, Point b, Point c, Point d){
        if (a!=null&&b!=null&&c!=null&&d!=null){
            setVertices(new ArrayList<>(Arrays.asList(a,b,c,d)));
        }else{
            LOGGER.fatal("Entity initialisation error - incorrect number of vertices passed into "+this.getClass()+", application stopped working");
            throw new RuntimeException();
        }
    }

    private void setSides (){// Defying array of 4 sides and notifying the observers
        sides = QuadragleCalculations.defineSides(vertices);
        notifyObservers();
    }

    public void logData(){
        LOGGER.info("Quadrangle id="+this.id+" has verticies "+vertices.get(0)+", "+
                vertices.get(1)+", "+
                vertices.get(2)+", "+
                vertices.get(3)+"\n");
    }

    @Override
    public void notifyObservers() {
        for (QuadrangleObserver o : observers){
            o.updateData(this);
        }
    }

    @Override
    public void addObserver(QuadrangleObserver o) {
        observers.add(o);
        notifyObservers();
    }

    @Override
    public void removeObserver(QuadrangleObserver o){
        observers.remove(o);
    }

    @Override
    public String toString() {
        String output = new String("");
        output+="A: ("+this.vertices.get(0).getX()+", "+this.vertices.get(0).getY()+" ) \n";
        output+="B: ("+this.vertices.get(1).getX()+", "+this.vertices.get(1).getY()+" ) \n";
        output+="C: ("+this.vertices.get(2).getX()+", "+this.vertices.get(2).getY()+" ) \n";
        output+="D: ("+this.vertices.get(3).getX()+", "+this.vertices.get(3).getY()+" ) \n";
        return output;
        }

}
