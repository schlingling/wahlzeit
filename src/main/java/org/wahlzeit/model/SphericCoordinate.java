package org.wahlzeit.model;

import org.wahlzeit.services.DataObject;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class SphericCoordinate extends AbstractCoordinate{

    private double phi;
    private double theta;
    private double radius;


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


    @Override
    public CartesianCoordinate asCartesianCoordinate() {
        return null;
    }

    @Override
    public SphericCoordinate asSphericCoordinate() {
        return null;
    }





    @Override
    public double doGetCartesianDistance() {
        return 0;
    }

    @Override
    public double doGetCentralAngel(Coordinate coordinate) {
        return 0;
    }

    @Override
    protected boolean doIsEqual(Coordinate coordinate) {
        return false;
    }

    @Override
    protected void doReadFrom(ResultSet resultSet) throws SQLException {

    }

    @Override
    protected void doWriteOn(ResultSet resultSet) throws SQLException {

    }


}
