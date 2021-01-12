package org.wahlzeit.model.location;


import org.wahlzeit.utils.BehaviouralDesignPatternInstance;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class CartesianCoordinate extends AbstractCoordinate {

    private final double x;
    private final double y;
    private final double z;

    protected static HashMap<Integer, CartesianCoordinate> hashMap = new HashMap<>();


    /**
     * Instanciate Location with specific Coordinates
     *
     * @methodtype constructor
     */
    private CartesianCoordinate(double x, double y, double z) {
        assertClassInvariants();
        assertArgumentNotNAN(x);
        assertArgumentNotNAN(y);
        assertArgumentNotNAN(z);
        this.x = rint(x, NACHKOMMASTELLEN);
        this.y = rint(y, NACHKOMMASTELLEN);
        this.z = rint(z, NACHKOMMASTELLEN);
        assertClassInvariants();
    }

    /**
     * Get or creates the Coordinate with given parameter.
     * Ensures, that Coordinate is immutable and shared!
     *
     * @methodtype helper
     */
    public static CartesianCoordinate getOrCreateCoordinate(double x, double y, double z) {
        CartesianCoordinate c = new CartesianCoordinate(x, y, z);
        int key = c.hashCode();
        synchronized (CartesianCoordinate.class) {
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
    public static CartesianCoordinate getOrCreateCoordinate() {
        return CartesianCoordinate.getOrCreateCoordinate(0, 0, 0);
    }

    /**
     * @methodtype get
     */
    public double getX() {
        assertClassInvariants();
        return x;
    }

    /**
     * @methodtype get
     */
    public double getY() {
        assertClassInvariants();
        return y;
    }

    /**
     * @methodtype get
     */
    public double getZ() {
        assertClassInvariants();
        return z;

    }


    /**
     * Calculates the distance between CartesianCoordinatecoordinate and this
     *
     * @methodtype query
     */
    public double getDistance(CartesianCoordinate coordinate) {
        assertClassInvariants();
        assertArgumentNotNull(coordinate);

        double a, b, c = 0;

        a = this.getX() - coordinate.getX();
        b = this.getY() - coordinate.getY();
        c = this.getZ() - coordinate.getZ();

        double distance = Math.sqrt(Math.pow(a, 2) + Math.pow(b, 2) + Math.pow(c, 2));
        assertValGreaterEqualsZero(distance);
        assertClassInvariants();
        return distance;
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
        result = (int) (31 * result + rint(getX(), NACHKOMMASTELLEN));
        result = (int) (31 * result + rint(getY(), NACHKOMMASTELLEN));
        result = (int) (31 * result + rint(getZ(), NACHKOMMASTELLEN));

        assertClassInvariants();
        return result;
    }

    /**
     * calculates CartesianCoordinatte from coordinates with 7 digits tolerance
     *
     * @methodtype query
     */
    @BehaviouralDesignPatternInstance(
            patternName = "Template Method",
            metaParticipants = {"Template Method", "Implementation Method"},
            participants = {"getCartesianDistance()", "asCartesianDistance"},
            roleOfAnnotatedMethod = "Implementation Method"
    )
    public CartesianCoordinate asCartesianCoordinate() {
        assertClassInvariants();
        return this;
    }


    /**
     * calculates Spheric coordinate from coordinates with 7 digits tolerance
     *
     * @methodtype query
     */
    public SphericCoordinate asSphericCoordinate() {
        assertClassInvariants();

        double phi; //azimuth, angle of rotation from the inital meridian plane--> x-axis
        double theta; //inclination, polar angle, fangle with respect to polar axis, from z-axis
        double radius; //Distance from origin

        double x, y, z;

        phi = Math.atan2(this.getY(), this.getX());
        if (this.getZ() != 0) {
            theta = Math.atan(
                    (Math.sqrt(Math.pow(this.getX(), 2) + Math.pow(this.getY(), 2)))
                            / this.getZ());
        } else {
            throw new ArithmeticException("Division by 0");
        }

        radius = Math.sqrt(Math.pow(this.getX(), 2) + Math.pow(this.getY(), 2) + Math.pow(this.getZ(), 2));

        assertValGreaterEqualsZero(radius);
        assertClassInvariants();
        SphericCoordinate sc = SphericCoordinate.getOrCreateCoordinate(phi, theta, radius);

        return sc;
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

        CartesianCoordinate c = coordinate.asCartesianCoordinate();

        int r1 = Double.compare(this.getX(), c.getX());
        int r2 = Double.compare(this.getY(), c.getY());
        int r3 = Double.compare(this.getZ(), c.getZ());
        return (r1 == 0) && (r2 == 0) && (r3 == 0);
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

        //Todo: Wie lösen??
        //this.setX(resultSet.getDouble("location_x"));
        //this.setY(resultSet.getDouble("location_y"));
        // this.setZ(resultSet.getDouble("location_z"));
        assertClassInvariants();

    }

    /**
     * Writes coordinates to database
     *
     * @methodtype mutation
     */
    @Override
    public void writeOn(ResultSet resultSet) throws SQLException {
        assertClassInvariants();
        assertArgumentNotNull(resultSet);
        resultSet.updateDouble("location_x", this.getX());
        resultSet.updateDouble("location_y", this.getY());
        resultSet.updateDouble("location_z", this.getZ());
        assertClassInvariants();

    }


    /**
     * Asserts Classinvariants
     *
     * @methodtype helper
     */
    @Override
    protected void assertClassInvariants() {
        if (Double.isNaN(this.x) || Double.isNaN(this.y) || Double.isNaN(this.z)) {
            throw new UncheckedCoordinateException("Classinvariant hurt");
        }

        super.assertClassInvariants();
    }
}

