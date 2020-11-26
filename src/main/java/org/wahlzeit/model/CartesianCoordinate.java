package org.wahlzeit.model;

import org.wahlzeit.services.DataObject;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CartesianCoordinate extends AbstractCoordinate {

    private double x;
    private double y;
    private double z;


    /**
     * Instanciate Coordinate.java with default values x=y=z=0
     *
     * @methodtype constructor
     */
    public CartesianCoordinate() {
        this.x = 0;
        this.y = 0;
        this.z = 0;
    }


    /**
     * Instanciate Location with specific Coordinates
     *
     * @methodtype constructor
     */
    public CartesianCoordinate(double x, double y, double z) {
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
     * Calculates the distance between Cartesian Coordinate.java coordinate and this.coordiante
     *
     * @methodtype query
     */
    public double getDistance(CartesianCoordinate coordinate) {
        if (coordinate == null) {
            throw new IllegalArgumentException("Coordinate.java c must be set to a value");
        }

        double a, b, c = 0;

        a = this.getX() - coordinate.getX();
        b = this.getY() - coordinate.getY();
        c = this.getZ() - coordinate.getZ();

        double distance = Math.sqrt(Math.pow(a, 2) + Math.pow(b, 2) + Math.pow(c, 2));
        return distance;
    }


    @Override
    public boolean equals(Object obj) {

        if (!(obj instanceof CartesianCoordinate) || !(this instanceof CartesianCoordinate)) {
            return false;
        }
        CartesianCoordinate cord = (CartesianCoordinate) obj;
        return this.isEqual(cord);
    }

    /**
     * calculates hashCode from coordinates with 7 digits tolerance
     *
     * @methodtype query
     */
    @Override
    public int hashCode() {
        //TODO: ggf in abstrakter Klasse definieren
        int result = 17;
        result = (int) (31 * result + rint(getX(), NACHKOMMASTELLEN));
        result = (int) (31 * result + rint(getY(), NACHKOMMASTELLEN));
        result = (int) (31 * result + rint(getZ(), NACHKOMMASTELLEN));
        return result;

    }


    @Override
    public CartesianCoordinate asCartesianCoordinate() {
        return this;
    }


    @Override
    public SphericCoordinate asSphericCoordinate() {

        double phi; //azimuth, angle of rotation from the inital meridian plane--> x-axis
        double theta; //inclination, polar angle, fangle with respect to polar axis, from z-axis
        double radius; //Distance from origin

        phi = Math.atan2(this.getY(), this.getX());
        theta = Math.atan((Math.sqrt(Math.pow(this.getX(), 2) + Math.pow(this.getY(), 2)) / this.getZ()));
        radius = Math.sqrt(Math.pow(this.getX(), 2) + Math.pow(this.getY(), 2) + Math.pow(this.getZ(), 2));

        return new SphericCoordinate(phi, theta, radius);
    }


    @Override
    protected boolean doIsEqual(Coordinate coordinate) {
        //TODO: ggf in abstrakter Klasse definieren
        CartesianCoordinate c = coordinate.asCartesianCoordinate();

        if (coordinate == null) {
            throw new IllegalArgumentException("Coordinate.java c must be set to a value");
        }

        return (compare(this.getX(), c.getX(), DELTA) &&
                compare(this.getY(), c.getY(), DELTA) &&
                compare(this.getZ(), c.getZ(), DELTA));
    }

    @Override
    protected void doReadFrom(ResultSet resultSet) throws SQLException {
        this.setX(resultSet.getDouble("location_x"));
        this.setY(resultSet.getDouble("location_y"));
        this.setZ(resultSet.getDouble("location_z"));
    }

    @Override
    protected void doWriteOn(ResultSet resultSet) throws SQLException {
        resultSet.updateDouble("location_x", this.getX());
        resultSet.updateDouble("location_y", this.getY());
        resultSet.updateDouble("location_z", this.getZ());
    }

}

