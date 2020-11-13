package org.wahlzeit.model;

public class Coordinate {

    private double x;
    private double y;
    private double z;

    public Coordinate(){
        this.x=0;
        this.y= 0;
        this.z=0;
    }

    public Coordinate(double x, double y, double z){
        this.x=x;
        this.y= y;
        this.z=z;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }
}
