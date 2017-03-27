package com.koshcheyev.quadrangle.observer;

/**
 * Created by Andrew on 21.03.2017.
 */
public interface ObservableEntity {

    void notifyObservers();
    void addObserver(QuadrangleObserver o);
    void removeObserver(QuadrangleObserver o);

}
