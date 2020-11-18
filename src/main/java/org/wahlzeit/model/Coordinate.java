package org.wahlzeit.model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Coordinate {

    private double x;
    private double y;
    private double z;

    /**
     * Instanciate Coordinate with default values x=y=z=0
     *
     * @methodtype constructor
     */
    public Coordinate() {
        this.x = 0;
        this.y = 0;
        this.z = 0;
    }


    /**
     * Instanciate Location with specific Coordinates
     *
     * @methodtype constructor
     */
    public Coordinate(double x, double y, double z) {
        setX(x);
        setY(y);
        setZ(z);
    }

    /**
     * @methodtype get
     */
    public double getX() {
        return x;
    }

    /**
     * @methodtype get
     */
    public double getY() {
        return y;
    }

    /**
     * @methodtype get
     */
    public double getZ() {
        return z;
    }

    /**
     * @methodtype set
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * @methodtype set
     */
    public void setY(double y) {
        this.y = y;
    }

    /**
     * @methodtype set
     */
    public void setZ(double z) {
        this.z = z;
    }

    /**
     * Calculates the distance between Cartesian Coordinate coordinate and this.coordiante
     *
     * @methodtype query
     */
    public double getDistance(Coordinate coordinate) {
        if (coordinate == null) {
            throw new IllegalArgumentException("Coordinate c must be set to a value");
        }

        double a, b, c = 0;

        a = this.getX() - coordinate.getX();
        b = this.getY() - coordinate.getY();
        c = this.getZ() - coordinate.getZ();

        double distance = Math.sqrt(Math.pow(a, 2) + Math.pow(b, 2) + Math.pow(c, 2));
        return distance;
    }

    /**
     * Compares if the Cartesion Coordiante coordinate is equal to this.coordinate with a maximum tolerance of 0.0000001
     *
     * @methodtype query
     */
    public boolean isEqual(Coordinate coordinate) {
        if (coordinate == null) {
            throw new IllegalArgumentException("Coordinate c must be set to a value");
        }

        if (compare(this.getX(), coordinate.getX(), 0.0000001) &&
                compare(this.getY(), coordinate.getY(), 0.0000001) &&
                compare(this.getZ(), coordinate.getZ(), 0.0000001)) {
            return true;
        }
        return false;
    }

    @Override
    public boolean equals(Object obj) {

        if(!(obj instanceof Coordinate)||!(this instanceof Coordinate)){
            return false;
        }
        Coordinate cord = (Coordinate) obj;
        return this.isEqual(cord);
    }

    /**
     * Compares two doubles with buffer-tolerance defined by epsilon
     *
     * @methodtype helper
     */
    public static boolean compare(double a, double b, double epsilon) {
        return Math.abs(a - b) < epsilon;
    }


    public void writeOn(ResultSet rset) throws SQLException {
        rset.updateDouble("location_x", this.getX());
        rset.updateDouble("location_y", this.getY());
        rset.updateDouble("location_z", this.getZ());
    }

}

