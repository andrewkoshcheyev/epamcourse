package com.koshcheyev.quadrangle.processing;

/**
 * Created by Admin on 23.03.2017.
 */
public class SimpleIDCounter {
    private static int id = 0;
    public static int defineNewID(){
        return id++;
    }
}
