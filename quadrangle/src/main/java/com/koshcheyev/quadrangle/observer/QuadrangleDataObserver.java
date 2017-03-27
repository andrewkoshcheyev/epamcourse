package com.koshcheyev.quadrangle.observer;

import com.koshcheyev.quadrangle.processing.AreaCalculator;
import com.koshcheyev.quadrangle.processing.PerimeterCalculator;
import com.koshcheyev.quadrangle.entity.Quadrangle;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * Created by Andrew on 21.03.2017.
 */
public class QuadrangleDataObserver implements QuadrangleObserver {
    private static final Logger LOGGER = Logger.getLogger(QuadrangleDataObserver.class);
    private HashMap<Integer, ArrayList<Double>> observables = new HashMap<>();

    public QuadrangleDataObserver(Quadrangle quadrangle) {
        this.observables.put(quadrangle.getID(), new ArrayList<>(Arrays.asList(new Double[]{PerimeterCalculator.calculatePerimeter(quadrangle), AreaCalculator.calculateArea(quadrangle)})));
        quadrangle.addObserver(this);
    }

    public HashMap<Integer, ArrayList<Double>> getObservables() {
        return observables;
    }

    public void  logData(){
        LOGGER.info(this.toString());
    }

    @Override
    public void updateData(Quadrangle o) {
        observables.put(o.getID(), new ArrayList<>(Arrays.asList(new Double[]{PerimeterCalculator.calculatePerimeter(o), AreaCalculator.calculateArea(o)})));
    }

    @Override
    public String toString() {
        String output = new String("");
        for (int i=0; i<observables.size();i++){
            output+="ObservableEntity entities: " +
                    observables.keySet().toArray()[i]+": Perimeter = "+
                    observables.get(observables.keySet().toArray()[i]).get(0)+" Area = "+
                    observables.get(observables.keySet().toArray()[i]).get(1)+"\n";
        }
        return output;
        }

}
