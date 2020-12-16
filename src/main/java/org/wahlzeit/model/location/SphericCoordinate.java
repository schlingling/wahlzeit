package org.wahlzeit.model.location;

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
    public SphericCoordinate()  {
        assertClassInvariants();

        this.setPhi(0);
        this.setRadius(0);
        this.setTheta(0);

        assertClassInvariants();

    }

    /**
     * Instanciate Location with specific values phi, theta, radius
     *
     * @methodtype constructor
     */

    public SphericCoordinate(double phi, double theta, double radius)   {
        assertClassInvariants();
        assertArgumentNotNAN(phi);
        assertArgumentNotNAN(theta);
        assertArgumentNotNAN(radius);

        this.setPhi(phi);
        this.setTheta(theta);
        this.setRadius(radius);

        assertClassInvariants();


    }

    /**
     * @methodtype set
     */
    public void setPhi(double phi) {
        assertClassInvariants();
        assertArgumentNotNAN(phi);

        this.phi = phi;
        assertClassInvariants();

    }

    /**
     * @methodtype set
     */
    public void setTheta(double theta) {
        assertClassInvariants();

        assertArgumentNotNAN(phi);

        this.theta = theta;
        assertClassInvariants();

    }

    /**
     * @methodtype set
     */
    public void setRadius(double radius) {
        assertClassInvariants();
        assertArgumentNotNAN(phi);

        this.radius = radius;
        assertClassInvariants();

    }

    /**
     * @methodtype get
     */
    public double getPhi() {
        assertClassInvariants();
        return this.phi;
    }

    /**
     * @methodtype get
     */
    public double getRadius() {
        assertClassInvariants();
        return this.radius;
    }

    /**
     * @methodtype get
     */
    public double getTheta() {
        assertClassInvariants();
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
        assertClassInvariants();

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
    public SphericCoordinate asSphericCoordinate()   {
        assertClassInvariants();
        return this;
    }


    /**
     * Checks wether this is equal to coordinate
     *
     * @methodtype query
     */
    protected boolean doIsEqual(Coordinate coordinate)   {
        assertClassInvariants();
        assertArgumentNotNull(coordinate);

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
        assertClassInvariants();
        assertArgumentNotNull(resultSet);

        this.setPhi(resultSet.getDouble("location_phi"));
        this.setTheta(resultSet.getDouble("location_theta"));
        this.setRadius(resultSet.getDouble("location_radius"));
        assertClassInvariants();

    }

    /**
     * y
     * Reads coordinates from database
     *
     * @methodtype mutation
     */
    @Override
    public void writeOn(ResultSet resultSet) throws SQLException {
        assertClassInvariants();
        assertArgumentNotNull(resultSet);

        resultSet.updateDouble("location_phi", this.getPhi());
        resultSet.updateDouble("location_theta", this.getTheta());
        resultSet.updateDouble("location_radius", this.getRadius());
        assertClassInvariants();

    }


    /**
     * calculates hashCode from coordinates with 7 digits tolerance
     *
     * @methodtype query
     */
    @Override
    protected int doHashCode() {
        assertClassInvariants();

        int result = 17;
        result = (int) (31 * result + rint(getPhi(), NACHKOMMASTELLEN));
        result = (int) (31 * result + rint(getTheta(), NACHKOMMASTELLEN));
        result = (int) (31 * result + rint(getRadius(), NACHKOMMASTELLEN));
        return result;

    }

    /**
     * @param coordinate
     * @return centralAngel between this and coordinate by using Radians
     */
    public double doGetCentralAngel(SphericCoordinate coordinate)   {
        assertClassInvariants();
        assertArgumentNotNull(coordinate);


        SphericCoordinate c1 = this.asSphericCoordinate();
        SphericCoordinate c2 = coordinate.asSphericCoordinate();


        double phiRadian1 = Math.toRadians(c1.getPhi());
        double thetaRadian1 = Math.toRadians(c1.getTheta());

        double phiRadian2 = Math.toRadians(c2.getPhi());
        double thetaRadian2 = Math.toRadians(c2.getTheta());

        double deltaThetaRadian = Math.abs(Math.abs(thetaRadian1) - Math.abs(thetaRadian2));

        double zähler = Math.sqrt(
                Math.pow((Math.cos(phiRadian2) * Math.sin(deltaThetaRadian)), 2) +
                        Math.pow(((Math.cos(phiRadian1) * Math.sin(phiRadian2)) - (Math.sin(phiRadian1) * Math.cos(phiRadian2) * Math.cos(deltaThetaRadian))), 2));
        double nenner = ((Math.sin(phiRadian1) * Math.sin(phiRadian2)) + (Math.cos(phiRadian1) * Math.cos(phiRadian2) * Math.cos(deltaThetaRadian)));

        double centralAngle;
        if (nenner != 0) {
            centralAngle = Math.atan(zähler / nenner);
        } else {
            throw new IllegalArgumentException("One of the coordinatevalues is 0; Division by 0 is forbidden");
        }
        return Math.toDegrees(centralAngle);

    }

    @Override
    protected void assertClassInvariants()  {
        if (Double.isNaN(this.phi) || Double.isNaN(this.theta) || Double.isNaN(this.radius)) {
            throw new UncheckedCoordinateException("Classinvariant hurt");
        }
        assertValGreaterEqualsZero(radius);
        super.assertClassInvariants();
    }
}


