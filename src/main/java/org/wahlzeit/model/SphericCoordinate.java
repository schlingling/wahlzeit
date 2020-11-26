package org.wahlzeit.model;

import org.wahlzeit.services.DataObject;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class SphericCoordinate extends AbstractCoordinate{


    //PHYSICAL REPRESENTATION  representation of Spherical coordinates
    //ATTENTION: NOT MATHEMATICAL REPRESENTATION --> phi and tetha NOT swapped (https://en.wikipedia.org/wiki/Spherical_coordinate_system)
    private double phi; //azimuth, angle of rotation from the inital meridian plane--> x-axis
    private double theta; //inclination, polar angle, fangle with respect to polar axis, from z-axis
    private double radius; //Distance from origin


    public SphericCoordinate() {
        this.setPhi(0);
        this.setRadius(0);
        this.setTheta(0);
    }


    public SphericCoordinate(double phi,  double theta, double radius) {
        this.setPhi(phi);
        this.setTheta(theta);
        this.setRadius(radius);

    }

    public void setPhi(double phi) {
        this.phi = phi;
    }

    public void setTheta(double theta) {
        this.theta = theta;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public double getPhi() {
        return this.phi;
    }

    public double getRadius() {
        return this.radius;
    }

    public double getTheta() {
        return this.theta;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SphericCoordinate c = (SphericCoordinate) o;
        return (compare(this.getPhi(), c.getPhi(), DELTA) &&
                compare(this.getTheta(), c.getTheta(), DELTA) &&
                compare(this.getRadius(), c.getRadius(), DELTA));
    }

    @Override
    public int hashCode() {
        return Objects.hash(phi, theta, radius); //andere Impl als in CartesianCoordinate
    }


    /**
     * Transforms Coordinate to CartesianCoordinate
     * @methodtype query
     * @return CartesianCoordinate
     */
    @Override
    public CartesianCoordinate asCartesianCoordinate() {

        double x = getRadius()*Math.sin(getTheta())*Math.cos(getPhi());
        double y = getRadius()*Math.sin(getTheta())*Math.sin(getPhi());
        double z = getRadius()*Math.cos(getTheta());

        return new CartesianCoordinate(x,y,z);
    }

    /**
     * Transforms Coordinate to SphericCoordinate
     * @methodtype query
     * @return spericCoordinate
     */
    @Override
    public SphericCoordinate asSphericCoordinate() {
        return this;
    }


    @Override
    protected boolean doIsEqual(Coordinate coordinate) {
        return false;
    }

    @Override
    protected void doReadFrom(ResultSet resultSet) throws SQLException {
        this.setPhi(resultSet.getDouble("location_phi"));
        this.setTheta(resultSet.getDouble("location_theta"));
        this.setRadius(resultSet.getDouble("location_radius"));
    }

    @Override
    protected void doWriteOn(ResultSet resultSet) throws SQLException {
        resultSet.updateDouble("location_phi", this.getPhi());
        resultSet.updateDouble("location_theta", this.getTheta());
        resultSet.updateDouble("location_radius", this.getRadius());
    }


}
