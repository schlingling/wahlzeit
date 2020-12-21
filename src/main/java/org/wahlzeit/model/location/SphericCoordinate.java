package org.wahlzeit.model.location;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class SphericCoordinate extends AbstractCoordinate {


    //PHYSICAL REPRESENTATION  representation of Spherical coordinates
    //ATTENTION: NOT MATHEMATICAL REPRESENTATION --> phi and tetha NOT swapped (https://en.wikipedia.org/wiki/Spherical_coordinate_system)
    private final double phi; //azimuth, angle of rotation from the inital meridian plane--> x-axis
    private final double theta; //inclination, polar angle, fangle with respect to polar axis, from z-axis
    private final double radius; //Distance from origin

    //Map for storing&sharing immutable coordinates
    private static HashMap<Integer, SphericCoordinate> hashMap = new HashMap();


    /**
     * Instanciate Location with specific values phi, theta, radius
     *
     * @methodtype constructor
     */

    private SphericCoordinate(double phi, double theta, double radius) {
        assertClassInvariants();
        assertArgumentNotNAN(phi);
        assertArgumentNotNAN(theta);
        assertArgumentNotNAN(radius);

        assertIsValidAngle(phi);
        assertIsValidAngle(theta);
        assertValGreaterEqualsZero(radius);

        this.phi = rint(phi,NACHKOMMASTELLEN);
        this.theta = rint(theta,NACHKOMMASTELLEN);
        this.radius = rint(radius,NACHKOMMASTELLEN);

        assertClassInvariants();
    }


    /**
     * Get or creates the Coordinate with given parameter
     * Ensures, that Coordinate is immutable and shared!
     *
     * @methodtype helper
     */
    public static SphericCoordinate getOrCreateCoordinate(double phi, double theta, double radius) {
        SphericCoordinate c = new SphericCoordinate(phi, theta, radius);
        int key = c.hashCode();
        synchronized (SphericCoordinate.class) {
            if (!hashMap.containsKey(key)) {
                hashMap.put(key, c);
            }
        }
        return hashMap.get(key);
    }


    /**
     * Get or creates the Coordinate with default parameter 0,0,0.
     * Ensures, that Coordinate is immutable and shared!
     *
     * @methodtype helper
     */
    public static SphericCoordinate getOrCreateDefaultCoordinate() {
        return getOrCreateCoordinate(0,0,0);
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
    public CartesianCoordinate asCartesianCoordinate() throws CheckedCoordinateException {
        assertClassInvariants();
        double x, y, z;
        try {
            x = getRadius() * Math.sin(getTheta()) * Math.cos(getPhi());
            y = getRadius() * Math.sin(getTheta()) * Math.sin(getPhi());
            z = getRadius() * Math.cos(getTheta());
        } catch (Exception e) {
            throw new CheckedCoordinateException("Fehler in der Umwandlung zu CartesianCoordinate", e);
        }
        return CartesianCoordinate.getOrCreateCoordinate(x, y, z);
    }

    /**
     * Transforms Coordinate to SphericCoordinate
     *
     * @return spericCoordinate
     * @methodtype query
     */
    @Override
    public SphericCoordinate asSphericCoordinate() throws CheckedCoordinateException {
        assertClassInvariants();
        return this;
    }



    /**
     * Checks wether this is equal to coordinate
     *
     * @methodtype query
     */
    @Override
    protected boolean doIsEqual(Coordinate coordinate) throws CheckedCoordinateException {
        assertClassInvariants();
        assertArgumentNotNull(coordinate);

        SphericCoordinate c = coordinate.asSphericCoordinate();

        int r1 = Double.compare(this.getPhi(), c.getPhi());
        int r2 = Double.compare(this.getTheta(), c.getTheta());
        int r3 = Double.compare(this.getRadius(), c.getRadius());
        return (r1==0)&&(r2==0)&&(r3==0);
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

        //Todo: Wie lösen?
        //this.setPhi(resultSet.getDouble("location_phi"));
       // this.setTheta(resultSet.getDouble("location_theta"));
        //this.setRadius(resultSet.getDouble("location_radius"));


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
    public int hashCode() {
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
    public double doGetCentralAngel(SphericCoordinate coordinate) throws CheckedCoordinateException {
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

        double res = Math.toDegrees(centralAngle);
        assertIsValidCalculatedAngle(res);
        assertClassInvariants();
        return res;

    }

    /**
     * Asserts class invariants
     *
     * @methodtype helper
     */
    @Override
    protected void assertClassInvariants() {
        if (Double.isNaN(this.phi) || Double.isNaN(this.theta) || Double.isNaN(this.radius)) {
            throw new UncheckedCoordinateException("Classinvariant hurt");
        }
        assertValGreaterEqualsZero(radius);
        super.assertClassInvariants();
    }

    /**
     * Asserts valid angle
     *
     * @methodtype helper
     */
    protected static void assertIsValidAngle(double angle) {
        if (angle < 0.0 || angle > 360.0) {
            throw new UncheckedCoordinateException("Central Angle should always be between 0 and 360 deg but was " + angle);
        }
    }

    /**
     * Assert valid angle
     *
     * @methodtype helper
     */
    protected static void assertIsValidCalculatedAngle(double angle) throws CheckedCoordinateException {
        if (angle < 0.0 || angle > 360.0) {
            throw new CheckedCoordinateException("Central Angle should always be between 0 and 360 deg but was " + angle);
        }
    }

}


