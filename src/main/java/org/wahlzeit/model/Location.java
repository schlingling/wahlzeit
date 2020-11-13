package org.wahlzeit.model;

public class Location {

    public Coordinate coordinate;

    public Location (){
        this.coordinate=new Coordinate();
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

}
