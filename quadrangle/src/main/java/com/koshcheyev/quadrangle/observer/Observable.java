package com.koshcheyev.quadrangle.observer;

/**
 * Created by Andrew on 21.03.2017.
 */
public interface Observable {

    void notifyObservers();
    void addObserver(QuadrangleObserver o);
    void removeObserver(QuadrangleObserver o);

}
