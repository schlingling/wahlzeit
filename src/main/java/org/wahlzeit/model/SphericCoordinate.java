package org.wahlzeit.model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SphericCoordinate extends AbstractCoordinate {


    //PHYSICAL REPRESENTATION  representation of Spherical coordinates
    //ATTENTION: NOT MATHEMATICAL REPRESENTATION --> phi and tetha NOT swapped (https://en.wikipedia.org/wiki/Spherical_coordinate_system)
    private double phi; //azimuth, angle of rotation from the inital meridian plane--> x-axis
    private double theta; //inclination, polar angle, fangle with respect to polar axis, from z-axis
    private double radius; //Distance from origin


    /**
     * Instanciate Coordinate.java with default values phi=theta=radius=0
     *
     * @methodtype constructor
     */
    public SphericCoordinate() {
        this.setPhi(0);
        this.setRadius(0);
        this.setTheta(0);
    }

    /**
     * Instanciate Location with specific values phi, theta, radius
     *
     * @methodtype constructor
     */

    public SphericCoordinate(double phi, double theta, double radius) {

        this.setPhi(phi);
        this.setTheta(theta);
        this.setRadius(radius);

    }

    /**
     * @methodtype set
     */
    public void setPhi(double phi) {
        this.phi = phi;
    }

    /**
     * @methodtype set
     */
    public void setTheta(double theta) {
        this.theta = theta;
    }

    /**
     * @methodtype set
     */
    public void setRadius(double radius) {
        this.radius = radius;
    }

    /**
     * @methodtype get
     */
    public double getPhi() {
        return this.phi;
    }

    /**
     * @methodtype get
     */
    public double getRadius() {
        return this.radius;
    }

    /**
     * @methodtype get
     */
    public double getTheta() {
        return this.theta;
    }


    /**
     * Transforms Coordinate to CartesianCoordinate
     *
     * @return CartesianCoordinate
     * @methodtype query
     */
    @Override
    public CartesianCoordinate asCartesianCoordinate() {

        double x = getRadius() * Math.sin(getTheta()) * Math.cos(getPhi());
        double y = getRadius() * Math.sin(getTheta()) * Math.sin(getPhi());
        double z = getRadius() * Math.cos(getTheta());

        return new CartesianCoordinate(x, y, z);
    }

    /**
     * Transforms Coordinate to SphericCoordinate
     *
     * @return spericCoordinate
     * @methodtype query
     */
    @Override
    public SphericCoordinate asSphericCoordinate() {
        return this;
    }


    /**
     * Checks wether this is equal to coordinate
     *
     * @methodtype query
     */
    @Override
    protected boolean doIsEqual(Coordinate coordinate) {
        SphericCoordinate c = coordinate.asSphericCoordinate();
                return (compare(this.getPhi(), c.getPhi(), DELTA) &&
                compare(this.getTheta(), c.getTheta(), DELTA) &&
                compare(this.getRadius(), c.getRadius(), DELTA));
    }

    /**
     * Reads coordinates from database
     *
     * @methodtype mutation
     */
    @Override
    public void readFrom(ResultSet resultSet) throws SQLException {
        this.setPhi(resultSet.getDouble("location_phi"));
        this.setTheta(resultSet.getDouble("location_theta"));
        this.setRadius(resultSet.getDouble("location_radius"));
    }

    /**
     * Reads coordinates from database
     *
     * @methodtype mutation
     */
    @Override
    public void writeOn(ResultSet resultSet) throws SQLException {
        resultSet.updateDouble("location_phi", this.getPhi());
        resultSet.updateDouble("location_theta", this.getTheta());
        resultSet.updateDouble("location_radius", this.getRadius());
    }

    /**
     * calculates hashCode from coordinates with 7 digits tolerance
     *
     * @methodtype query
     */
    @Override
    public int hashCode() {
        int result = 17;
        result = (int) (31 * result + rint(getPhi(), NACHKOMMASTELLEN));
        result = (int) (31 * result + rint(getTheta(), NACHKOMMASTELLEN));
        result = (int) (31 * result + rint(getRadius(), NACHKOMMASTELLEN));
        return result;

    }

}
