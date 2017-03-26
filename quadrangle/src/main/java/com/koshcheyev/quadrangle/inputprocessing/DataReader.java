package com.koshcheyev.quadrangle.inputprocessing;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

import com.koshcheyev.quadrangle.entity.Point;
import org.apache.log4j.Logger;


/**
 * Created by Andrew on 21.03.2017.
 */
public class DataReader {

    private ArrayList<Double []> coordinates;
    private ArrayList<ArrayList<Point>> objectsVerticies;
    private static final Logger LOGGER = Logger.getLogger(DataReader.class);
    private static final int POINTS_COUNT = 8;

    public DataReader(String path) {
        this.coordinates = readFromFile(path);
    }

    public ArrayList<Double []> getCoordinates() {
        return coordinates;
    }

    public ArrayList<ArrayList<Point>> getObjectsVertices(){
        defineVertices();
        return objectsVerticies;
    }

    private void defineVertices(){
        objectsVerticies = new ArrayList<>();
        ArrayList<Double []> coordinatesSet = getCoordinates();
        for (Double[] verticesCoordinates : coordinatesSet) {
            objectsVerticies.add(new ArrayList<>(Arrays.asList(new Point(verticesCoordinates[0],verticesCoordinates[1]),
                    new Point(verticesCoordinates[2],verticesCoordinates[3]),
                    new Point(verticesCoordinates[4],verticesCoordinates[5]),
                    new Point(verticesCoordinates[6],verticesCoordinates[7]))));
        }
    }

    private ArrayList<Double[]> readFromFile(String path){ // Returns an array of coordinate sets, checked for the right points count and for incorrect format
        try{
            BufferedReader br = new BufferedReader(new FileReader(path));
            try{
                ArrayList<String> lines = new ArrayList<>();
                String line;
                while ((line = br.readLine())!=null) {// Creating array of lines from file
                    lines.add(line);
                }
                br.close();
                ArrayList<Double[]> output = new ArrayList<>();
                try{
                    for (String s : lines){ // Creating array of double[] that stores the coordinates
                        Double[] points = new Double[POINTS_COUNT];
                        String[] values = s.split(" ");
                        if (values.length!= POINTS_COUNT){
                            LOGGER.info("Input coordinates is incorrect - wrong number of points in line \""+s+"\"\n");
                        }else {
                            try{
                                for (int i = 0; i < values.length; i++) { // Parsing and checking doubles
                                    points[i] = Double.parseDouble(values[i]);
                                }
                                output.add(points);
                            }catch (NumberFormatException e){
                                LOGGER.info("Input coordinates is incorrect - wrong coordinates format in line \""+s+"\"\n");
                            }
                        }
                    }
                    return output;
                }catch (NullPointerException e){
                    LOGGER.fatal("Something went wrong while reading the file\n");
                    throw new RuntimeException();
                }
            }catch (IOException e){
                LOGGER.fatal("Something went wrong while reading the file\n");
                throw new RuntimeException();

            }
        }catch(FileNotFoundException e) {
            LOGGER.fatal("File " + path + " is missing\n");
            throw new RuntimeException();
        }
    }
}
