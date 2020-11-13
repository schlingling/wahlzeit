package org.wahlzeit.model;

public class Coordinate {

    private double x;
    private double y;
    private double z;

    /**
     *  Instanciate Coordinate with default values x=y=z=0
     * @methodtype constructor
     */
    public Coordinate(){
        this.x=0;
        this.y= 0;
        this.z=0;
    }


    /**
     *  Instanciate Location with specific Coordinates
     * @methodtype constructor
     */
    public Coordinate(double x, double y, double z){
        setX(x);
        setY(y);
        setZ(z);
    }

    /**
     *
     * @methodtype get
     */
    public double getX() {
        return x;
    }

    /**
     *
     * @methodtype get
     */
    public double getY() {
        return y;
    }

    /**
     *
     * @methodtype get
     */
    public double getZ() {
        return z;
    }

    /**
     *
     * @methodtype set
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     *
     * @methodtype set
     */
    public void setY(double y) {
        this.y = y;
    }

    /**
     *
     * @methodtype set
     */
    public void setZ(double z) {
        this.z = z;
    }
}
