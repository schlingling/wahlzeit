package org.wahlzeit.model.location;

import org.wahlzeit.services.DataObject;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public abstract class AbstractCoordinate extends DataObject implements Coordinate {

    /**
     * Konstanten, für Toleranz bei double Vergleich
     */
    protected final double DELTA = 0.0000001;
    protected final int NACHKOMMASTELLEN = 7;


    protected abstract int doHashCode();

    protected abstract boolean doIsEqual(Coordinate coordinate);


    /**
     * @param coordinate
     * @return distance between this and coordinate
     */
    @Override
    public double getCartesianDistance(Coordinate coordinate) throws CheckedCoordinateException {
        assertClassInvariants();
        assertArgumentNotNull(coordinate);

        double result = doGetCartesianDistance(coordinate);
        //Post-Condition
        assertIsValidDistance(result);
        return result;
    }


    /**
     * @param coordinate
     * @return central angle between this and coordinate
     */
    @Override
    public double getCentralAngel(Coordinate coordinate) throws CheckedCoordinateException {
        assertClassInvariants();
        assertArgumentNotNull(coordinate);
        SphericCoordinate sc1 = this.asSphericCoordinate();
        SphericCoordinate sc2 = coordinate.asSphericCoordinate();

        double res = sc1.doGetCentralAngel(sc2);
        assertIsValidCentralAngle(res);
        assertClassInvariants();
        return res;
    }


    /**
     * Checks wether this is equal to coordinate
     *
     * @return true if represenation in Cartesian AND Spheric is equal
     * @methodtype query
     */
    @Override
    public boolean isEqual(Coordinate coordinate) {
        assertClassInvariants();
        assertArgumentNotNull(coordinate);
        if (!(coordinate instanceof Coordinate) || !(this instanceof Coordinate)) {
            return false;
        }

        CartesianCoordinate cc1 = this.asCartesianCoordinate();
        CartesianCoordinate cc2 = coordinate.asCartesianCoordinate();

        SphericCoordinate sc1 = this.asSphericCoordinate();
        SphericCoordinate sc2 = coordinate.asSphericCoordinate();

        boolean check1 = cc1.doIsEqual(cc2);
        boolean check2 = sc1.doIsEqual(sc2);

        assertClassInvariants();
        return check1 && check2;
    }


    @Override
    public String getIdAsString() {
        assertClassInvariants();

        //NOT IMPLEMENTED
        return null;
    }


    @Override
    public void writeId(PreparedStatement stmt, int pos) throws SQLException {
        assertClassInvariants();

        assertArgumentNotNull(stmt);
        //donothing
    }


    @Override
    public boolean isDirty() {
        assertClassInvariants();

        return writeCount != 0;
    }

    /**
     * Checks wether obj equals this, deligates to isEqual()
     *
     * @param obj
     * @return
     */
    @Override
    public boolean equals(Object obj) {
        assertClassInvariants();
        assertArgumentNotNull(obj);
        if (!(obj instanceof Coordinate) || !(this instanceof Coordinate)) {
            return false;
        }
        Coordinate cord = (Coordinate) obj;


        try {
            return this.isEqual(cord);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public int hashCode() {
        assertClassInvariants();
        return doHashCode();

    }

    /**
     * @param coordinate
     * @return cartesian Distance between this and coordinate
     */
    protected double doGetCartesianDistance(Coordinate coordinate) throws CheckedCoordinateException {
        assertClassInvariants();
        assertArgumentNotNull(coordinate);
        CartesianCoordinate c1 = this.asCartesianCoordinate();
        CartesianCoordinate c2 = coordinate.asCartesianCoordinate();

        double result = c1.getDistance(c2);
        assertIsValidDistance(result);
        assertClassInvariants();
        return result;
    }

    /**
     * Compares two doubles with buffer-tolerance defined by epsilon
     *
     * @methodtype helper
     */
    protected static boolean compare(double a, double b, double epsilon) {
        assertArgumentNotNAN(a);
        assertArgumentNotNAN(b);
        assertArgumentNotNAN(epsilon);

        return Math.abs(a - b) < epsilon;
    }

    /**
     * Rints a double value by decimalPoints
     *
     * @methodtype helper
     */
    protected static double rint(double value, int decimalPoints) {
        assertArgumentNotNAN(value);
        assertValGreaterEqualsZero(decimalPoints);

        double d = Math.pow(10, decimalPoints);
        return Math.rint(value * d) / d;
    }

    /**
     * Assert classInvariants from AbstractClass --> should be called from Subclasses when they override
     *
     * @methodtype helper
     */
    protected void assertClassInvariants() {

    }


    /**
     * Assert valid argument
     *
     * @methodtype helper
     */
    protected static void assertArgumentNotNull(Object obj) {
        if (obj == null) {
            throw new IllegalArgumentException("Argument is null");
        }
        ;
    }

    protected static void assertArgumentNotNAN(double argument) {
        if (Double.isNaN(argument)) {
            throw new IllegalArgumentException("Argument is nan");
        }
        ;
    }

    protected static void assertIsValidCentralAngle(double angle) {
        if (angle < 0.0 || angle > 360.0) {
            throw new UncheckedCoordinateException("Central Angle should always be between 0 and 360 deg but was " + angle);
        }
    }

    protected static void assertIsValidDistance(double angle) throws CheckedCoordinateException {
        if (angle < 0.0 || angle > 360.0) {
            throw new CheckedCoordinateException("Central Angle should always be between 0 and 360 deg but was " + angle);
        }
    }


    protected static void assertValGreaterEqualsZero(double argument) {
        if (argument < 0) {
            throw new IllegalArgumentException("Argument should be greater euquals Zero");

        }
    }


}
